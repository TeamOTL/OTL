package com.otl.otl.service;

import com.otl.otl.domain.MemberStudy;
import com.otl.otl.dto.StudyListDTO;

import java.util.List;

public interface MemberStudyService {

    List<MemberStudy> findByMemberEmail(String email);



    //  <<< ------나의 스터디----- >>>
    /*  WHERE email = ? AND is_accpeted = 1;
        - 나의 스터디 다건 조회
     */
    List<StudyListDTO> getMyStudyAccpted(String email);


    /*  email = ? AND is_accpted = 1 AND sno = ?;
        - 나의 스터디 단건 조회
     */
    List<StudyListDTO> getMyStudyAccptedAndSno(String email, Long sno);


    /*  INSERT INTO task ( , , , , ) VALUES ( , , , , , , );
        - 나의 스터디 - 회의록 작성
     */

    /*  UPDATE task SET ?, ?, ?, ?, ? WHERE sno = ? AND tno = ?;
        - 나의 스터디 - 회의록 작성
     */



    //  <<< ------방장 페이지------ >>>
    /*  WHERE sno = ? AND is_accpeted = 1 AND is_managed = 1 ;
        - 방장 페이지 -> 참가 중인 멤버 조회
    */
    List<StudyListDTO> findParticipant(Long sno, Boolean isAccepted, Boolean isManaged);


    /*  WHERE sno = ? AND is_accpeted = 0 AND is_managed = 1 ;
       - 방장 페이지 -> 참가 대기 멤버 조회 (GET)
    */
    List<StudyListDTO> findWaitingParticipant(Long sno, Boolean isAccepted, Boolean isManaged);


    /*  UPDATE member_study SET is_accepted = 1 WHERE email = ? AND sno = ?
       - 방장 페이지 -> 참가 신청 멤버 관리 <신청 승인> (PUT)
    */
    void AcceptParticipant(String email, Long sno);


    /*  //DELETE FROM member_study WHERE email = ? AND sno = ?
         - 방장 페이지 -> 참가 신청 멤버 관리 < 거절 >(PUT)
      */
    void refuseParticipant(String email, Long sno);
    //레포 메서드 검증 필요



    /*  UPDATE member_study SET is_accepted = 0 WHERE email = ? AND sno = ?
       - 방장 페이지 - 참가 중인 멤버 강퇴 (PUT)
    */
    void RemoveParticipant(String email, Long sno);

    List<StudyListDTO> getMyStudyAcceptedAndSno(String email, Long sno);


    /*   UPDATE study SET ( , , , , , , );      ([study] + cno = ? + [interests] + [task] )
            - 방장 페이지 -> 스터디 관리 (스터디 수정) (PUT)
         */




















    //  <<<  ----- 페이지----- >>>
    /*  ;
        -
     */



}
