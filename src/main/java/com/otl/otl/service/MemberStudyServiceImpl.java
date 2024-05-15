package com.otl.otl.service;

import com.otl.otl.domain.MemberStudy;
import com.otl.otl.domain.Study;
import com.otl.otl.dto.MemberStudyDTO;
import com.otl.otl.dto.MemberStudyProjection.MemberStudyProjectionImpl;
import com.otl.otl.dto.StudyListDTO;
import com.otl.otl.repository.MemberStudyRepository;
import com.otl.otl.service.converter.CustomConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberStudyServiceImpl implements MemberStudyService {

    private final MemberStudyRepository memberStudyRepository;
    private final CustomConverters customConverters;

    @Autowired
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





    //  <---------------------------- 나의 스터디 ---------------------------->
    /*  WHERE email = ? AND is_accpeted = 1;
        - (참가 중인) 나의 스터디 다건 조회
     */
//    @Override
//    public List<MemberStudyProjectionImpl> getMyStudyAccpted(String email) {
//        return memberStudyRepository.findMemberStudyByEmailAndIsAcceptedProjection(email);
//    }
    @Override
    public List<StudyListDTO> getMyStudyAccpted(String email) {
        List<MemberStudyProjectionImpl> projections = memberStudyRepository.findMemberStudyByEmailAndIsAcceptedProjection(email);
        return projections.stream()
                .map(customConverters::ProjectionToDTO)
                .collect(Collectors.toList());
    }



    /*  email = ? AND is_accpted = 1 AND sno = ?;
        - (참가 중인) 나의 스터디 단건 조회
     */
//    @Override
//    public List<MemberStudy> getMyStudyAccptedAndSno(String email, Long sno) {
//        return memberStudyRepository.findMemberStudyByEmailAndIsAcceptedAndSno(email, sno);
//    }
    @Override
    public List<StudyListDTO> getMyStudyAccptedAndSno(String email, Long sno) {
        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberStudyByEmailAndIsAcceptedAndSno(email, sno);
        return memberStudyList.stream()
                .map(customConverters::memberStudyToDTO)
                .collect(Collectors.toList());
    }



     /*  INSERT INTO task ( , , , , ) VALUES ( , , , , , , );
        - 나의 스터디 - 회의록 작성
     */

    /*  UPDATE task SET ?, ?, ?, ?, ? WHERE sno = ? AND tno = ?;
        - 나의 스터디 - 회의록 작성
     */




    //  <---------------------------- 방장 페이지 ---------------------------->
    /*  WHERE sno = ? AND is_accpeted = 1 AND is_managed = 1 ;
        - 방장 페이지 -> 참가 중인 멤버 조회 (GET)
     */
//    @Override
//    public List<MemberStudy> findParticipant(Long sno, Boolean isAccepted, Boolean isManaged) {
//        return memberStudyRepository.findMemberBySnoAndIsAccepted(sno, isAccepted, isManaged);
//    }
    @Override
    public List<StudyListDTO> findParticipant(Long sno, Boolean isAccepted, Boolean isManaged) {
        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberBySnoAndIsAccepted(sno, isAccepted, isManaged);
        return memberStudyList.stream()
                .map(customConverters::memberStudyToDTO)
                .collect(Collectors.toList());
    }

    /*  WHERE sno = ? AND is_accpeted = 0 AND is_managed = 1 ;
       - 방장 페이지 -> 참가 대기 멤버 조회 (GET)
    */
//    @Override
//    public List<MemberStudy> findWaitingParticipant(Long sno, Boolean isAccepted, Boolean isManaged) {
//        return memberStudyRepository.findMemberBySnoAndAcceptedYet(sno, isAccepted, isManaged);
//    }
    @Override
    public List<StudyListDTO> findWaitingParticipant(Long sno, Boolean isAccepted, Boolean isManaged) {
        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberBySnoAndAcceptedYet(sno, isAccepted, isManaged);
        return memberStudyList.stream()
                .map(customConverters::memberStudyToDTO)
                .collect(Collectors.toList());
    }


    /*  UPDATE member_study SET is_accepted = 1 WHERE email = ? AND sno = ?
       - 방장 페이지 -> 참가 신청 멤버 관리 <신청 승인> (PUT)
    */
    public void AcceptParticipant(String email, Long sno) {
        memberStudyRepository.updateIsAcceptedByEmailAndSno(email, sno);
    }


    /*  DELETE FROM member_study WHERE email = ? AND sno = ?
        - 방장 페이지 -> 참가 신청 멤버 관리 < 거절 >(PUT)
     */
    @Override
    public void refuseParticipant(String email, Long sno) {
        memberStudyRepository.deleteMemberStudyByEmailAndSno(email, sno);
    }


    /*  UPDATE member_study SET is_accepted = 0 WHERE email = ? AND sno = ?
        - 방장 페이지 - 참가 중인 멤버 강퇴 (PUT)
     */
    @Override
    public void RemoveParticipant(String email, Long sno) {
        memberStudyRepository.updateIsAcceptedRefuseByEmailAndSno(email, sno);
    }

    @Override
    public List<StudyListDTO> getMyStudyAcceptedAndSno(String email, Long sno) {
        return List.of();
    }



    /*   UPDATE study SET ( , , , , , , );      ([study] + cno = ? + [interests] + [task] )
        - 방장 페이지 -> 스터디 관리 (스터디 수정) (PUT)
     */










}
