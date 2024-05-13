package com.otl.otl.service;

import com.otl.otl.domain.*;
import com.otl.otl.dto.*;
import com.otl.otl.repository.CategoryRepository;
import com.otl.otl.repository.MemberStudyRepository;
import com.otl.otl.repository.StudyRepository;
import com.otl.otl.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class StudyServiceImpl implements StudyService {
    private final ModelMapper modelMapper;
    private  final CategoryRepository categoryRepository;
    private final StudyRepository studyRepository;
    private final TaskRepository taskRepository;
    private final MemberStudyService memberStudyService;

    private final MemberStudyRepository memberStudyRepository;

    @Override
    public List<StudyListDTO> getAllStudyJoin() {
        return List.of();
    }
//    private final StudyListDTOConverter studyListDTOConverter;


//
//        public StudyListDTO toDTO(Study study) {
//            // StudyListDTO 객체 생성
//            StudyListDTO studyListDTO = StudyListDTO.builder()
//                    // Study 정보 설정
//                    .sno(study.getSno())
//                    .studyName(study.getStudyName())
//                    .studyDescription(study.getStudyDescription())
//                    .studyPlan(study.getStudyPlan())
//                    .maxMember(study.getMaxMember())
//                    .firstDate(study.getFirstDate())
//                    .rStartDate(study.getRStartDate())
//                    .rEndDate(study.getREndDate())
//                    .dDay(study.getDDay())
//                    .cno(study.getCno())
//                    // Category 정보 설정
//                    .categoryName(study.getCategoryDTO().getCategoryName())
//                    .categoryImage(study.getCategoryDTO().getCategoryImage())
//                    // Interests 정보 설정
//                    .ino(study.getInterestsDTO().getIno())
//                    .interestName(study.getInterestsDTO().getInterestName())
//                    // Member 정보 설정
//                    .mno(study.getMember().getMno())
//                    .email(study.getMember().getEmail())
//                    .nickname(study.getMember().getNickname())
//                    .memberProfileImage(study.getMember().getMemberProfileImage())
//                    .memberDescription(study.getMember().getMemberDescription())
//                    // MemberStudy 정보 설정
//                    .msNo(study.getMemberStudyDTO().getMsNo())
//                    .isAccepted(study.getMemberStudyDTO().isAccepted())
//                    .isManaged(study.getMemberStudyDTO().isManaged())
//                    .comment(study.getMemberStudyDTO().getComment())
//                    // Task 정보 설정
//                    .tno(study.getTaskDTO().getTno())
//                    .taskWeek(study.getTaskDTO().getTaskWeek())
//                    .taskTitle(study.getTaskDTO().getTaskTitle())
//                    .taskDate(study.getTaskDTO().getTaskDate())
//                    .taskTime(study.getTaskDTO().getTaskTime())
//                    .taskPlace(study.getTaskDTO().getTaskPlace())
//                    .taskMember(study.getTaskDTO().getTaskMember())
//                    .taskContent(study.getTaskDTO().getTaskContent())
//                    .isCompleted(study.getTaskDTO().isCompleted())
//                    .planDate(study.getTaskDTO().getPlanDate())
//                    .build();
//
//            return studyListDTO;
//        }
//
//
//
//
//
//

    @Override
    public Long register(StudyDTO studyDTO) {
        return null;
    }

    @Override
    public StudyDTO readStudy(Long sno) {
        return null;
    }

    @Override
    public void modify(StudyDTO studyDTO) {

    }

    @Override
    public void remove(Long sno) {

    }

    @Override
    public Long addStudy(StudyDTO studyDTO) {

        // BoardDTO를 Board 엔티티로 변환
        Study study = Study.builder()
                .studyName(studyDTO.getStudyName())
                .studyDescription(studyDTO.getStudyDescription())
//                .studyPlan(studyDTO.getStudyPlan())
                .maxMember(studyDTO.getMaxMember())
                .rStartDate(studyDTO.getRStartDate())
                .rEndDate(studyDTO.getREndDate())// 초기값 설정
                .build();

        // 생성된 Board 객체를 저장하고, 저장된 게시글의 ID를 반환합니다.
        Study saveStudy = studyRepository.save(study);
        log.info("새로운 스터디룸 등록되었습니다: 게시글 ID {}", saveStudy.getSno());

        return saveStudy.getSno();
    }


    // author : 99duuk


    /*
        SELECT * FROM study WHERE cno = ?
        카테고리 드롭다운 선택 -> 해당 카테고리 스터디 다건 조회
     */


    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    //모든 스터디 조회
    @Override
    public List<Study> getStudies() {
        return studyRepository.findAll();
    }

    // cno 별 Study조회
    @Override
    public List<Study> getStudiesByCno(Long cno) {
        return studyRepository.findByCategoryCno(cno);
    }

//    @Override
//    public Optional<Study> getStudiesByIno(Long ino) {
//        return studyRepository.findByInterestsIno(ino);
//    }


//    @Override
//    public List<StudyListDTO> getAllStudyJoin() {
//        return studyRepository.findAllByCurDate()
//                .stream()
//                .map(studyListDTOConverter::convertToDTO) // Convert Study to StudyListDTO
//                .collect(Collectors.toList());
//    }

    @Override
    public List<Study> getAllStudyJoin2() {
        return studyRepository.findAllByCurDate();
    }

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


}
