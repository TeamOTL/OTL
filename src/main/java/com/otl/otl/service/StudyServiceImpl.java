package com.otl.otl.service;

import com.otl.otl.domain.*;
import com.otl.otl.dto.*;
import com.otl.otl.dto.customDTO.StudyCreateCustomDTO;
import com.otl.otl.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private final MemberRepository memberRepository;
    private final MemberStudyService memberStudyService;

    private final MemberStudyRepository memberStudyRepository;

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
}
