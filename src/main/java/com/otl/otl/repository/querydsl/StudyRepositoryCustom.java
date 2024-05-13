package com.otl.otl.repository.querydsl;

import com.otl.otl.domain.MemberStudy;
import com.otl.otl.domain.Study;
import com.otl.otl.dto.StudyListDTO;

import java.util.List;

public interface StudyRepositoryCustom {

    // is_accpeted = 1
    List<MemberStudy> findMemberStudyByIsAccepted();


//    List<Long> findSnoByEmailAndAccepted(String email);
    List<Long> findSnoByEmailAndIsAccepted(String email);

    // email = ? AND is_accpeted = 1;   //참가 중  //다건 날짜 계산 적용
    List<MemberStudy> findMemberStudyByEmailAndIsAccepted(String email);



    // email = ? AND sno = ? AND is_accepted = 1; 참가중 단건  :   /sno=?
    List<MemberStudy> findMemberStudyByEmailAndIsAcceptedAndSno(String email, Long sno);


    List<Study> findAllByCurDate();
    // email = ? AND is_accepted = 0; 참가 대기 중

    List<StudyListDTO> findAllByCurDate2();

    // SELECT ms.eamil, ms.sno, m.nickname, m.member_profile_image
    // email = ? AND sno = ? AND is_managed = 1; 방장 관리


    // UPDATE member_study SET is_accepted = 1 WHERE email = ?,


    // sno = ? AND is_accepted = 0; 방장 페이지 -> 참가 신청 중 다건 조회
    // SELECT m.member_prpfile_imgae, m.nickname, i.interests_name, ms.comment
    // FROM member_study ms
    // WHERE sno = ?
    // AND is_accepted = 0;



    // sno = ? AND is_accpeted = 1; 방장 페이지 -> 참가 중인 멤버 조회
    /*
    SELECT ms.emil, ms.sno, m.member_profile_image, m.nickname
    FROM member_study ms
    WHERE sno = ?,
    AND is_accepted = 1;
     */


    //task



//    List<Study> findStudy(String email);


}
