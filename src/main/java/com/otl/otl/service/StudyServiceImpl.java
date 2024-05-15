//package com.otl.otl.service;
//
//import com.otl.otl.domain.*;
//import com.otl.otl.dto.*;
//import com.otl.otl.dto.StudyCreateCustomDTO;
//import com.otl.otl.dto.customDTO.StudyCreateCustomDTO;
//import com.otl.otl.repository.*;
//import com.otl.otl.utils.StudyConverters;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//import java.util.stream.Collectors;
//
//@Service
//@Log4j2
//@RequiredArgsConstructor
//@Transactional
//public class StudyServiceImpl implements StudyService {
//    private final ModelMapper modelMapper;
//    private  final CategoryRepository categoryRepository;
//    private final StudyRepository studyRepository;
//    private final TaskRepository taskRepository;
//    private final MemberRepository memberRepository;
//    private final MemberStudyService memberStudyService;
//
//    private final MemberStudyRepository memberStudyRepository;
//
//    @Autowired
//    private StudyConverters studyConverters;
//
//    @Override
//    public List<StudyListDTO> getAllStudyJoin() {
//        return List.of();
//    }
////    private final StudyListDTOConverter studyListDTOConverter;
//
//
////
////        public StudyListDTO toDTO(Study study) {
////            // StudyListDTO 객체 생성
////            StudyListDTO studyListDTO = StudyListDTO.builder()
////                    // Study 정보 설정
////                    .sno(study.getSno())
////                    .studyName(study.getStudyName())
////                    .studyDescription(study.getStudyDescription())
////                    .studyPlan(study.getStudyPlan())
////                    .maxMember(study.getMaxMember())
////                    .firstDate(study.getFirstDate())
////                    .startDate(study.getstartDate())
////                    .(study.get())
////                    .dDay(study.getDDay())
////                    .cno(study.getCno())
////                    // Category 정보 설정
////                    .categoryName(study.getCategoryDTO().getCategoryName())
////                    .categoryImage(study.getCategoryDTO().getCategoryImage())
////                    // Interests 정보 설정
////                    .ino(study.getInterestsDTO().getIno())
////                    .interestName(study.getInterestsDTO().getInterestName())
////                    // Member 정보 설정
////                    .mno(study.getMember().getMno())
////                    .email(study.getMember().getEmail())
////                    .nickname(study.getMember().getNickname())
////                    .memberProfileImage(study.getMember().getMemberProfileImage())
////                    .memberDescription(study.getMember().getMemberDescription())
////                    // MemberStudy 정보 설정
////                    .msNo(study.getMemberStudyDTO().getMsNo())
////                    .isAccepted(study.getMemberStudyDTO().isAccepted())
////                    .isManaged(study.getMemberStudyDTO().isManaged())
////                    .comment(study.getMemberStudyDTO().getComment())
////                    // Task 정보 설정
////                    .tno(study.getTaskDTO().getTno())
////                    .taskWeek(study.getTaskDTO().getTaskWeek())
////                    .taskTitle(study.getTaskDTO().getTaskTitle())
////                    .taskDate(study.getTaskDTO().getTaskDate())
////                    .taskTime(study.getTaskDTO().getTaskTime())
////                    .taskPlace(study.getTaskDTO().getTaskPlace())
////                    .taskMember(study.getTaskDTO().getTaskMember())
////                    .taskContent(study.getTaskDTO().getTaskContent())
////                    .isCompleted(study.getTaskDTO().isCompleted())
////                    .planDate(study.getTaskDTO().getPlanDate())
////                    .build();
////
////            return studyListDTO;
////        }
////
////
////
////
////
////
//
//    @Override
//    public Study register(StudyCreateCustomDTO studyCreateCustomDTO, String email) {
//
//        Category category = categoryRepository.findById(studyCreateCustomDTO.getCno())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
//
//        Study study = studyConverters.StudyCreateCustomDTOToDomain(studyCreateCustomDTO, category);
//        Study savedStudy = studyRepository.saveAndFlush(study);
//
//        Member member = memberRepository.findByEmail(email)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid member email"));
//
//        MemberStudy memberStudy = MemberStudy.builder()
//                .member(member)
//                .study(savedStudy)
//                .isManaged(true)
//                .build();
//
//        memberStudyRepository.saveAndFlush(memberStudy);
//
//        return savedStudy;
//    }
//
//
////    @Transactional
////    @Override
////    public Long register(StudyCreateDTO studyCreateDTO, String email){
////        log.info("Registering study with DTO: {}", studyCreateDTO);
////        log.info("Category ID: {}", studyCreateDTO.getCno());
////        log.info("Email: {}", email);
////
////        // 카테고리 조회
////        Category category = categoryRepository.findById(studyCreateDTO.getCno())
////                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
////
////        // Study 엔티티 생성
////        Study study = Study.builder()
////                .studyName(studyCreateDTO.getStudyName())
////                .studyDescription(studyCreateDTO.getStudyDescription())
////                .studyPlan(studyCreateDTO.getStudyPlan())
////                .maxMember(studyCreateDTO.getMaxMember())
////                .firstDate(studyCreateDTO.getFirstDate())
////                .rStartDate(studyCreateDTO.getRStartDate())
////                .rEndDate(studyCreateDTO.getREndDate())
////                .category(category)
////                .build();
////
////
//////             Task 추가
////        for (TaskDTO taskDTO : studyCreateDTO.getTasks()) {
////            study.addTask(taskDTO.getTaskDate(), taskDTO.getTaskTitle());
////        }
////
//////             Interest 추가
////        for (InterestsDTO interestDTO : studyCreateDTO.getIntestes()) {
////            study.addInterest(interestDTO.getInterestName());
////        }
////
////
////        log.info(study);
////        // D-Day 계산 및 설정
//////            study.setDDay(study.calCombinedDday());
////        // Study 엔티티 저장
////        Study savedStudy = studyRepository.saveAndFlush(study);
////
////
////
////
////
////
////        // Member 조회
////        Member member = memberRepository.findByEmail(email)
////                .orElseThrow(() -> new IllegalArgumentException("Invalid member email"));
////
////        // MemberStudy 엔티티 생성 및 저장
////        MemberStudy memberStudy = MemberStudy.builder()
////                .member(member)
////                .study(savedStudy)
////                .isManaged(true) // 방장으로 설정
////                .build();
////
////        memberStudyRepository.saveAndFlush(memberStudy);
////
////        log.info("Study registered with ID: {}", savedStudy.getSno());
////
////        return savedStudy.getSno();
////    }
//
//    @Override
//    public StudyDTO readStudy(Long sno) {
//        return null;
//    }
//
//
//
//
//    // author : 99duuk
//
//
//    /*
//        SELECT * FROM study WHERE cno = ?
//        카테고리 드롭다운 선택 -> 해당 카테고리 스터디 다건 조회
//     */
//
//
//    @Override
//    public List<Category> getAllCategories() {
//        return categoryRepository.findAll();
//    }
//
//
//    //모든 스터디 조회
//    @Override
//    public List<Study> getStudies() {
//        return studyRepository.findAll();
//    }
//
//    // cno 별 Study조회
//    @Override
//    public List<Study> getStudiesByCno(Long cno) {
//        return studyRepository.findByCategoryCno(cno);
//    }
//
////    @Override
////    public Optional<Study> getStudiesByIno(Long ino) {
////        return studyRepository.findByInterestsIno(ino);
////    }
//
//
////    @Override
////    public List<StudyListDTO> getAllStudyJoin() {
////        return studyRepository.findAllByCurDate()
////                .stream()
////                .map(studyListDTOConverter::convertToDTO) // Convert Study to StudyListDTO
////                .collect(Collectors.toList());
////    }
//
//    @Override
//    public List<Study> getAllStudyJoin2() {
//        return studyRepository.findAllByCurDate();
//    }
//
//    @Override
//    public Study getStudyById(Long sno) {
//        return studyRepository.findById(sno).orElse(null);
//    }
//
//    @Override
//    public List<Study> findUserStudies(String email) {
//        List<MemberStudy> memberStudies = memberStudyRepository.findByMemberEmail(email);
//        return memberStudies.stream()
//                .map(MemberStudy::getStudy)
//                .collect(Collectors.toList());
//    }
//
//
//}
