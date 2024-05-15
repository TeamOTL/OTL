package com.otl.otl.service;

import com.otl.otl.domain.Category;
import com.otl.otl.domain.Member;
import com.otl.otl.domain.MemberStudy;
import com.otl.otl.domain.Study;
import com.otl.otl.dto.customDTO.StudyCreateCustomDTO;
import com.otl.otl.repository.CategoryRepository;
import com.otl.otl.repository.MemberRepository;
import com.otl.otl.repository.MemberStudyRepository;
import com.otl.otl.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudyService {

    private final StudyRepository studyRepository;
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;
    private final MemberStudyRepository memberStudyRepository;

//    @Transactional
//    public void createStudy(StudyCreateCustomDTO studyCreateCustomDTO, String email) {
//        // 카테고리 조회
//        Category category = categoryRepository.findById(studyCreateCustomDTO.getCno())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
//
//        // Study 엔티티 생성
//        Study study = Study.builder()
//                .studyName(studyCreateCustomDTO.getStudyName())
//                .studyDescription(studyCreateCustomDTO.getStudyDescription())
//                .studyPlan(studyCreateCustomDTO.getStudyPlan())
//                .maxMember(studyCreateCustomDTO.getMaxMember())
//                .firstDate(studyCreateCustomDTO.getFirstDate())
//                .rStartDate(studyCreateCustomDTO.getRStartDate()) // 기본값 사용
//                .rEndDate(studyCreateCustomDTO.getREndDate()) // 기본값 사용
//                .category(category)
//                .build();
//
//        // Task 추가
//        studyCreateCustomDTO.getCustomTasks().forEach(taskDTO ->
//                study.addTask(taskDTO.getTaskDate(), taskDTO.getTaskTitle())
//        );
//
//        // Interest 추가
//        studyCreateCustomDTO.getCustomInterests().forEach(interestDTO ->
//                study.addInterest(interestDTO.getInterestName())
//        );
//
//        // D-Day 계산 및 설정
//        study.setDDay(study.calCombinedDday());
//
//        // Study 엔티티 저장
//        Study savedStudy = studyRepository.saveAndFlush(study);
//
//        // Member 조회
//        Member member = memberRepository.findByEmail(email)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid member email"));
//
//        // MemberStudy 엔티티 생성 및 저장
//        MemberStudy memberStudy = MemberStudy.builder()
//                .member(member)
//                .study(savedStudy)
//                .isManaged(true) // 방장으로 설정
//                .build();
//
//        memberStudyRepository.saveAndFlush(memberStudy);
//    }

    @Transactional
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
}

