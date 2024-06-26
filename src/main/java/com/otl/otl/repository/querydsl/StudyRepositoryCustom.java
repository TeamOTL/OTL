package com.otl.otl.repository.querydsl;

import com.otl.otl.domain.MemberStudy;
import com.otl.otl.domain.Study;
import com.otl.otl.domain.Task;
import com.otl.otl.dto.MemberStudyProjection.MemberStudyProjectionImpl;
import com.querydsl.core.Tuple;
import jakarta.transaction.Transactional;

import java.util.List;

public interface StudyRepositoryCustom {


    //전체 멤버 테이블 빼고 보내기
//    List<Long> findSnoByEmailAndAccepted(String email);

    // email = ? AND is_accpeted = 1;   //참가 중  //다건 날짜 계산 적용
    List<MemberStudy> findMemberStudyByEmailAndIsAccepted(String email);

    // email = ? AND is_accpeted = 1;   //참가 중  //다건 날짜 계산 적용 projection
    List<MemberStudyProjectionImpl> findMemberStudyByEmailAndIsAcceptedProjection(String email);

    // email = ?  AND is_accepted = 1 AND sno = ?; 참가중 단건  :   /sno=?
    List<MemberStudy> findMemberStudyByEmailAndIsAcceptedAndSno(String email, Long sno);

    List<MemberStudyProjectionImpl> findMemberStudyByEmailAndIsAcceptedAndSnoProjection(String email, Long sno);

    // SELECT ms.eamil, ms.sno, m.nickname, m.member_profile_image
    // email = ? AND sno = ? AND is_managed = 1; 방장 관리 페이지
    List<MemberStudy> findMemberStudyByEmailAndSnoAndIsManaged(String email, Long sno, Boolean isManaged);


    List<Tuple> findAllAddCount();



    // DELETE FROM member_study WHERE email = ? AND sno = ?
    @Transactional
    void deleteTask(Long sno, Long tno);


    @Transactional
    void updateTask(Long tno, String taskTitle, String taskDate, String taskTime, String taskPlace, String taskMember, String taskContent);

    // sno = ? AND is_accpeted = 1; 방장 페이지 -> 참가 중인 멤버 조회 (강퇴)
    /*
    SELECT ms.emil, ms.sno, m.member_profile_image, m.nickname
    FROM member_study ms
    WHERE sno = ?,
    AND is_accepted = 1;
     */
    List<MemberStudy> findMemberBySnoAndIsAccepted(Long sno);





    // UPDATE member_study SET is_accepted = 1 WHERE email = ? AND sno = ?
    void updateIsAcceptedByEmailAndSno(String email, Long sno);




    List<Tuple> countAcceptedBySno();


    // UPDATE member_study SET is_accepted = 1 WHERE email = ? AND sno = ?
    @Transactional
    void updateIsAcceptedRefuseByEmailAndSno(String email, Long sno);

    // email = ? AND is_accepted = 0; 참가 대기 중
    List<Study> findAllByCurDate();
    // email = ? AND is_accepted = 0 AND cno = ?;
    List<Study> findAllByCurDateByCno(Long cno);
    // email = ? AND is_accepted = 0 AND study_name LIKE '%?%;
    List<Study> findAllByCurDateByKeyword(String keyword);






    @Transactional
    void deleteMemberStudyByEmailAndSno(String email, Long sno);

    // sno = ? AND is_accpeted = ; 방장 페이지 -> 참가 대기 멤버 조회
    /*
    SELECT ms.emil, ms.sno, m.member_profile_image, m.nickname
    FROM member_study ms
    WHERE sno = ?,
    AND is_accepted = 0;
     */
    List<MemberStudy> findMemberBySnoAndAcceptedYet(Long sno);


}
