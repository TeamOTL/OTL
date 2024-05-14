package com.otl.otl.service;

import com.otl.otl.domain.MemberStudy;
import com.otl.otl.domain.Study;
import com.otl.otl.dto.MemberStudyProjection.MemberStudyProjectionImpl;

import java.util.List;

public interface MemberStudyService {

    List<MemberStudy> findByMemberEmail(String email);

//  <<< 나의 스터디 >>>
    /*  WHERE email = ? AND is_accpeted = 1;
        - 나의 스터디 다건 조회
     */
    List<MemberStudy> getMyStudyAccpted(String email);


    /*  email = ? AND is_accpted = 1 AND sno = ?;
        - 나의 스터디 단건 조회
     */
    List<MemberStudy> getMyStudyAccptedAndSno(String email, Long sno);


    /*  INSERT INTO task ( , , , , ) VALUES ( , , , , , , );
        - 나의 스터디 - 회의록 작성
     */

    /*  Update task SET ?, ?, ?, ?, ? WHERE sno = ? AND tno = ?;
        - 나의 스터디 - 회의록 작성
     */



    //  <<< 방장 페이지 >>>
    /*  WHERE sno = ? AND is_accpeted = 1 AND is_managed = 1 ;
        - 방장 페이지 -> 참가 중인 멤버 조회
    */
    List<MemberStudy> findMemberBySnoAndIsAccepted(Long sno, Boolean isAccepted, Boolean isManaged);


    /*  UPDATE member_study SET is_accepted = 1 WHERE email = ? AND sno = ?
        - 방장 페이지 - 참가 중인 멤버 강퇴
     */
    void RemoveParticipant(String email, Long sno);


    /*   ;  [study], cno, interests.interestName, [task]
        - 방장 페이지 -> 스터디 관리 (스터디 수정)
     */


    //  <<< 방장 페이지 >>>
    /*  ;
        -
     */



}
