package com.otl.otl.service;

import com.otl.otl.domain.MemberStudy;
import com.otl.otl.domain.Study;
import com.otl.otl.dto.MemberStudyDTO;
import com.otl.otl.dto.MemberStudyProjection.MemberStudyProjectionImpl;
import com.otl.otl.dto.StudyListDTO;
import com.otl.otl.repository.MemberStudyRepository;
import com.otl.otl.service.converter.CustomConverters;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberStudyServiceImpl implements MemberStudyService {

    private final MemberStudyRepository memberStudyRepository;
    private final CustomConverters customConverters;


    public MemberStudyServiceImpl(MemberStudyRepository memberStudyRepository, StudyService studyService, CustomConverters customConverters) {
        this.memberStudyRepository = memberStudyRepository;
        this.customConverters = customConverters;
    }

    // 컨버터
    public StudyListDTO ProjectionToDTO(MemberStudyProjectionImpl projection) {
        return customConverters.ProjectionToDTO(projection);
    }
    public StudyListDTO StudyToDto(Study study) {
        return customConverters.StudyToDto(study);
    }
    public MemberStudy MemberStudyDTOToDomain(MemberStudyDTO memberStudyDTO, MemberStudy memberStudy) {
        return customConverters.MemberStudyDTOToDomain(memberStudyDTO, memberStudy);
    }

    @Override
    public List<MemberStudy> findByMemberEmail(String email) {
        return memberStudyRepository.findByMemberEmail(email);
    }
//  <<<  >>>





//  <<< 나의 스터디 >>>
    /*  WHERE email = ? AND is_accpeted = 1;
        - (참가 중인) 나의 스터디 다건 조회
     */
    @Override
    public List<MemberStudy> getMyStudyAccpted(String email) {
        return memberStudyRepository.findMemberStudyByEmailAndIsAccepted(email);
    }


    /*  email = ? AND is_accpted = 1 AND sno = ?;
        - (참가 중인) 나의 스터디 단건 조회
     */
    @Override
    public List<MemberStudy> getMyStudyAccptedAndSno(String email, Long sno) {
        return memberStudyRepository.findMemberStudyByEmailAndIsAcceptedAndSno(email, sno);
    }




//  <<< 방장 페이지 >>>
    /*  WHERE sno = ? AND is_accpeted = 1 AND is_managed = 1 ;
        - 방장 페이지 -> 참가 중인 멤버 조회 (강퇴)
     */
    @Override
    public List<MemberStudy> findMemberBySnoAndIsAccepted(Long sno, Boolean isAccepted, Boolean isManaged) {
        return memberStudyRepository.findMemberBySnoAndIsAccepted(sno, isAccepted, isManaged);
    }


    /*  UPDATE member_study SET is_accepted = 1 WHERE email = ? AND sno = ?
        - 방장 페이지 - 참가 중인 멤버 강퇴
     */
    @Override
    public void RemoveParticipant(String email, Long sno) {
        memberStudyRepository.updateIsAcceptedByEmailAndSno(email, sno);
    }


    /*   ;  [study], cno, interests.interestName, [task]
        - 방장 페이지 -> 스터디 관리 (스터디 수정)
     */


    /*  ;
        -
     */




    //    @Override
//    public List<TodolistDTO> getAllTodos(String email) {
//        return todolistRepository.findByMemberEmail(email).stream()
//                .map(this::toDTO)
//                .collect(Collectors.toList());
//    }
}
