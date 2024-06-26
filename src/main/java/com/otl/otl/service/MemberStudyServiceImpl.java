package com.otl.otl.service;

import com.otl.otl.domain.Member;
import com.otl.otl.domain.MemberStudy;
import com.otl.otl.domain.Study;
import com.otl.otl.dto.MemberStudyDTO;
import com.otl.otl.dto.MemberStudyProjection.MemberStudyProjectionImpl;
import com.otl.otl.dto.StudyListDTO;
import com.otl.otl.repository.MemberRepository;
import com.otl.otl.repository.MemberStudyRepository;
import com.otl.otl.repository.StudyRepository;
import com.otl.otl.service.converter.CustomConverters;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class MemberStudyServiceImpl implements MemberStudyService {

    private final MemberStudyRepository memberStudyRepository;
    private final CustomConverters customConverters;
    private final StudyRepository studyRepository; // 지오 추가
    private final MemberRepository memberRepository; // 지오 추가

    @Autowired
    public MemberStudyServiceImpl(MemberStudyRepository memberStudyRepository, StudyService studyService, CustomConverters customConverters, StudyRepository studyRepository, MemberRepository memberRepository) {
        this.memberStudyRepository = memberStudyRepository;
        this.customConverters = customConverters;
        this.studyRepository = studyRepository;
        this.memberRepository = memberRepository;
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
        log.info(memberStudyList);
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
    public List<StudyListDTO> findParticipant(Long sno) {
        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberBySnoAndIsAccepted(sno);
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
    public List<StudyListDTO> findWaitingParticipant(Long sno) {
        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberBySnoAndAcceptedYet(sno);
        log.info(memberStudyList);
        return memberStudyList.stream()
                .map(customConverters::memberStudyToDTO)
                .collect(Collectors.toList());
    }

    // 지오 만듦
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
