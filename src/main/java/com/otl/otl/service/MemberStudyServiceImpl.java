package com.otl.otl.service;

import com.otl.otl.domain.*;
import com.otl.otl.dto.MemberStudyDTO;
import com.otl.otl.dto.MemberStudyProjection.MemberStudyProjectionImpl;
import com.otl.otl.dto.StudyListDTO;
import com.otl.otl.repository.MemberRepository;
import com.otl.otl.repository.MemberStudyRepository;
import com.otl.otl.repository.StudyRepository;
import com.otl.otl.service.converter.CustomConverters;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MemberStudyServiceImpl implements MemberStudyService {

    private final StudyRepository studyRepository;
    private final MemberRepository memberRepository;

    private final MemberStudyRepository memberStudyRepository;
//    private final CustomConverters customConverters;

//    public MemberStudyServiceImpl(MemberStudyRepository memberStudyRepository, CustomConverters customConverters) {
//        this.memberStudyRepository = memberStudyRepository;
//        this.customConverters = customConverters;
//    }
//
//    // 컨버터
//    public StudyListDTO ProjectionToDTO(MemberStudyProjectionImpl projection) {
//        return customConverters.ProjectionToDTO(projection);
//    }
//    public StudyListDTO StudyToDto(Study study) {
//        return customConverters.StudyToDto(study);
//    }
//    public MemberStudy MemberStudyDTOToDomain(MemberStudyDTO memberStudyDTO, MemberStudy memberStudy) {
//        return customConverters.MemberStudyDTOToDomain(memberStudyDTO, memberStudy);
//    }
//
//
//
//    @Override
//    public List<MemberStudy> findByMemberEmail(String email) {
//        return memberStudyRepository.findByMemberEmail(email);
//    }

    @Override
    public MemberStudy requestStudy(Long sno, String email, String comment) {
        // Study 조회
        Study study = studyRepository.findById(sno)
                .orElseThrow(() -> new IllegalArgumentException("Invalid study ID"));

        // Member 조회
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member email"));

        // MemberStudy 엔티티 생성 및 저장
        MemberStudy memberStudy = MemberStudy.builder()
                .member(member)
                .study(study)
                .isAccepted(false) // 신청 시에는 미승인 상태로 설정
                .isManaged(false)  // 방장이 아님
                .comment(comment)
                .build();

        memberStudyRepository.saveAndFlush(memberStudy);

        return memberStudy;
    }

    @Override
    public List<MemberStudy> readRequest(Long sno) {

        // Study 조회
        Study study = studyRepository.findById(sno)
                .orElseThrow(() -> new IllegalArgumentException("Invalid study ID"));

        // MemberStudy 조회
        List<MemberStudy> memberStudies = memberStudyRepository.findByStudyAndIsAcceptedAndIsManaged(study, false, false);

        // 조회된 결과가 비어 있는지 확인하고 비어 있으면 예외를 던집니다.
        if (memberStudies.isEmpty()) {
            throw new IllegalArgumentException("No member study found");
        }

        return memberStudies;

//        // Study 조회
//        Study study = studyRepository.findById(sno)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid study ID"));
//
//        // MemberStudy 조회
//        List<MemberStudy> memberStudies = memberStudyRepository.findByStudyAndIsAcceptedAndIsManaged(study, false, false);
//
//        // 조회된 결과가 비어 있는지 확인하고 비어 있으면 예외를 던집니다.
//        if (memberStudies.isEmpty()) {
//            throw new IllegalArgumentException("No member study found");
//        }
//
//        // MemberStudy 엔티티의 member 필드만 EAGER 로딩으로 설정
//        for (MemberStudy memberStudy : memberStudies) {
//            memberStudy.getMember().getInterests().size(); // member 필드의 interests를 가져옴
//        }
//
//        return memberStudies;

//        // Study 조회
//        Study study = studyRepository.findById(sno)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid study ID"));
//
//        // MemberStudy 조회
//        List<MemberStudy> memberStudies = memberStudyRepository.findByStudyAndIsAcceptedAndIsManaged(study, false, false);
//
//        // 조회된 결과가 비어 있는지 확인하고 비어 있으면 예외를 던집니다.
//        if (memberStudies.isEmpty()) {
//            throw new IllegalArgumentException("No member study found");
//        }
//
//        // MemberStudy 엔티티의 member 필드만 EAGER 로딩으로 설정
//        for (MemberStudy memberStudy : memberStudies) {
//            memberStudy.getMember().getInterests().size(); // member 필드의 interests를 가져옴
//            // Study 필드의 tasks를 Lazy로 설정
//            Hibernate.initialize(memberStudy.getStudy().getTasks());
//        }
//
//        return memberStudies;
    }
}
