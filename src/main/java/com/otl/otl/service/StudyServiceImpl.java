package com.otl.otl.service;

import com.otl.otl.domain.*;
import com.otl.otl.dto.*;
import com.otl.otl.dto.customDTO.StudyCreateCustomDTO;
import com.otl.otl.repository.*;
import com.otl.otl.service.converter.CustomConverters;
import com.querydsl.core.Tuple;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;


@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class StudyServiceImpl implements StudyService {
    private final ModelMapper modelMapper;
    private  final CategoryRepository categoryRepository;
    private final StudyRepository studyRepository; // 필드 추가
    private final TaskRepository taskRepository;
    private final MemberStudyRepository memberStudyRepository;
    private final MemberRepository memberRepository;
    private final CustomConverters customConverters; // Inject the CustomConverters component


    // <----------------------------------------- salmon ------------------------------------------------->
    @Override
    public Study getStudyById(Long sno) {
        return studyRepository.findById(sno).orElse(null);
    }

    @Override
    public List<Study> findUserStudies(String email) {
        List<MemberStudy> memberStudies = memberStudyRepository.findByMemberEmail(email);
        return memberStudies.stream()
                .map(MemberStudy::getStudy)
                .collect(Collectors.toList());
    }



// <----------------------------------------- sue ------------------------------------------------->

    @Override
    public void createStudy(StudyCreateCustomDTO studyCreateCustomDTO, String email) {
        // 카테고리 조회
        Category category = categoryRepository.findById(studyCreateCustomDTO.getCno())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));

        // Study 엔티티 생성
        Study study = Study.builder()
                .studyName(studyCreateCustomDTO.getStudyName())
                .studyDescription(studyCreateCustomDTO.getStudyDescription())
                .studyPlan(studyCreateCustomDTO.getStudyPlan())
                .maxMember(studyCreateCustomDTO.getMaxMember())
                .firstDate(studyCreateCustomDTO.getFirstDate())
                .rStartDate(studyCreateCustomDTO.getRStartDate())
                .rEndDate(studyCreateCustomDTO.getREndDate())
                .category(category)
                .build();

        // Task 추가
        studyCreateCustomDTO.getCustomTasks().forEach(taskDTO ->
                study.addTask(taskDTO.getTaskDate(), taskDTO.getTaskTitle())
        );

        // Interest 추가
        studyCreateCustomDTO.getCustomInterests().forEach(interestDTO ->
                study.addInterest(interestDTO.getInterestName())
        );

        // D-Day 계산 및 설정
        study.setDDay(study.calCombinedDday());

        // Study 엔티티 저장
        Study savedStudy = studyRepository.saveAndFlush(study);

        // Member 조회
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member email"));

        // MemberStudy 엔티티 생성 및 저장
        MemberStudy memberStudy = MemberStudy.builder()
                .member(member)
                .study(savedStudy)
                .isManaged(true) // 방장으로 설정
                .build();

        memberStudyRepository.saveAndFlush(memberStudy);
    }



// <----------------------------------------- 99duuk ------------------------------------------------->

    //  <<< 모집 페이지 >>>
    /*   SELECT * FROM category
        - 카테고리 전체 조회 (For 카테고리 검색)
     */
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }



    /*   ( 2024-05-20 -> d-13 )
        - 모집 중인 스터디 다건 조회 (모집 종료일이 지나지 않은)
     */
    @Override
    public List<StudyListDTO> findOpenStudies() {
        List<Study> studyList = studyRepository.findAllByCurDate();

        // 승인된 참가자 수를 모든 스터디에 대해 한 번만 조회
        Map<Long, Long> acceptedCountMap = new HashMap<>();
        List<Tuple> acceptedCountList = studyRepository.countAcceptedBySno();

        for (Tuple tuple : acceptedCountList) {
            Long sno = tuple.get(0, Long.class); // 첫 번째 값은 스터디의 고유 번호
            Long acceptedCount = tuple.get(1, Long.class); // 두 번째 값은 승인된 참가자 수
            acceptedCountMap.put(sno, acceptedCount);
        }

        // 각 스터디의 D-day 및 People 필드 설정
        for (Study study : studyList) {
            String dDay = study.calCombinedDday();
            study.setDDay(dDay);
            log.info(dDay);

            // 해당 스터디의 승인된 참가자 수 설정
            Long acceptedCount = acceptedCountMap.get(study.getSno());
            if (acceptedCount != null) {
                study.setPeople(acceptedCount.toString());
                log.info(acceptedCount);
            }
        }
        return studyList.stream()
                .map(customConverters::StudyToDto)
                .collect(Collectors.toList());
    }



    /*   ( WHERE category.cno = ?; 카테고리 드룹다운 선택 submit )
        - 모집 중인 스터디 검색 다건 조회 [카테고리 검색]
     */
    @Override
    public List<StudyListDTO> findOpenStudiesByCno(Long cno) {
        List<Study> studyList = studyRepository.findAllByCurDateByCno(cno);

        // 승인된 참가자 수를 모든 스터디에 대해 한 번만 조회
        Map<Long, Long> acceptedCountMap = new HashMap<>();
        List<Tuple> acceptedCountList = studyRepository.countAcceptedBySno();

        for (Tuple tuple : acceptedCountList) {
            Long sno = tuple.get(0, Long.class); // 첫 번째 값은 스터디의 고유 번호
            Long acceptedCount = tuple.get(1, Long.class); // 두 번째 값은 승인된 참가자 수
            acceptedCountMap.put(sno, acceptedCount);
        }

        for (Study study : studyList) {
            String dDay = study.calCombinedDday();
            study.setDDay(dDay);
            log.info(dDay);

            // 해당 스터디의 승인된 참가자 수 설정
            Long acceptedCount = acceptedCountMap.get(study.getSno());
            if (acceptedCount != null) {
                study.setPeople(acceptedCount.toString());
                log.info(acceptedCount);
            }
        }
        return studyList.stream()
                .map(customConverters::StudyToDto)
                .collect(Collectors.toList());
    }


    /*  LIKE "%?%"
        - [키워드 검색] 모집 중인 스터디 다건 조회
     */
    @Override
    public List<StudyListDTO> findOpenStudiesByKeyword(String keyword) {
        List<Study> studyList = studyRepository.findAllByCurDateByKeyword(keyword);

        // 승인된 참가자 수를 모든 스터디에 대해 한 번만 조회
        Map<Long, Long> acceptedCountMap = new HashMap<>();
        List<Tuple> acceptedCountList = studyRepository.countAcceptedBySno();

        for (Tuple tuple : acceptedCountList) {
            Long sno = tuple.get(0, Long.class); // 첫 번째 값은 스터디의 고유 번호
            Long acceptedCount = tuple.get(1, Long.class); // 두 번째 값은 승인된 참가자 수
            acceptedCountMap.put(sno, acceptedCount);
        }

        for (Study study : studyList) {
            String dDay = study.calCombinedDday();
            study.setDDay(dDay);
            log.info(dDay);

            // 해당 스터디의 승인된 참가자 수 설정
            Long acceptedCount = acceptedCountMap.get(study.getSno());
            if (acceptedCount != null) {
                study.setPeople(acceptedCount.toString());
                log.info(acceptedCount);
            }
        }
        return studyList.stream()
                .map(customConverters::StudyToDto)
                .collect(Collectors.toList());
    }


      /*   INSERT INTO member_study VALUES      (email = ?, sno = ?, is_managed = 0, is_accpeted = 0;)
        - 모집 페이지 [참가 신청] post 요청
     */


    /*   INSERT INTO study VALUES   (,, ,, , , , ,)   ([study] + cno = ? + [interests] + [task] )
        - 스터디 생성
     */

}
