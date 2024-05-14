
package com.otl.otl.repository;

import com.otl.otl.domain.MemberStudy;
import com.otl.otl.dto.MemberStudyProjection.MemberStudyProjection;
import com.otl.otl.dto.MemberStudyProjection.MemberStudyProjectionImpl;
import com.otl.otl.service.StudyService;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Log4j2
@SpringBootTest
@Transactional
public class MemberStudyRepositoryTests {

    @Autowired
    private MemberStudyRepository memberStudyRepository;

    @Autowired
    private StudyService studyService;

    @Autowired
    private StudyRepository studyRepository;


    //     <참가중 다건>
    //     WHERE email = ?
    //     AND is_accpeted = 1
    // [member null]
    @Test
    public void findByEmailAndIsAccepted() {
        // Given
        String email = "test1"; // 테스트할 member

        // When
        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberStudyByEmailAndIsAccepted(email);

        //Then
        log.info(memberStudyList);
    }
/*
    [MemberStudy(msNo=6, member=null, study=Study(sno=2, studyName=ㅁㄴㅇㄹ, studyDescription=2ㅇㄹㅁㅇㄴㄹㅁㄴㅇㄹ, studyPlan=ㅇㅇㅇ, maxMember=2, firstDate=2024-05-24, rStartDate=2024-05-05, rEndDate=2024-05-23,
    category=Category(cno=2, categoryName=프론트엔드, categoryImage=22), dDay=D-9,
     tasks=[Task(tno=2, taskTitle=ㄴㅁㅇㄹ, taskDate=ㅁㄹㅇㅁ, taskTime=aaa, taskPlace=13, taskMember=33, taskContent=2024-05-30, isCompleted=true),
        Task(tno=4, taskTitle=ㅁㄴㅇㄹ, taskDate=ㅁㄹㅇㅁ, taskTime=aaa, taskPlace=13, taskMember=33, taskContent=2024-05-31, isCompleted=true),
        Task(tno=6, taskTitle=ㅠㅁ, taskDate=ㅁㄹㅇㅁ, taskTime=aaa, taskPlace=13, taskMember=33, taskContent=2024-06-30, isCompleted=true),
        Task(tno=8, taskTitle=ㅠㅁㅊ, taskDate=ㅁㄹㅇㅁ, taskTime=aaa, taskPlace=13, taskMember=33, taskContent=2024-06-31, isCompleted=true)],
        interests=[Interests(ino=2, interestName=리눅스, 도커)]), isAccepted=true, isManaged=false, comment=afdsfasdf)]
 */



//     <참가중 다건  - Projection 적용 member 제외>
//     WHERE email = ?
//     AND is_accpeted = 1
    @Test
    public void findMemberStudyByEmailAndIsAcceptedProJectionMember(){
        // Given
        String email = "test1"; // 테스트할 member

        // When
        List<MemberStudyProjectionImpl> memberStudyList = memberStudyRepository.findMemberStudyByEmailAndIsAcceptedProjection(email);

        //Then
        log.info(memberStudyList);

    }

    /*
         Study: Study(sno=2, studyName=ㅁㄴㅇㄹ, studyDescription=2ㅇㄹㅁㅇㄴㄹㅁㄴㅇㄹ, studyPlan=ㅇㅇㅇ, maxMember=2, firstDate=2024-05-24, rStartDate=2024-05-05, rEndDate=2024-05-23,
         category=Category(cno=2, categoryName=프론트엔드, categoryImage=22), dDay=null,
        tasks=[Task(tno=2, taskTitle=ㄴㅁㅇㄹ, taskDate=ㅁㄹㅇㅁ, taskTime=aaa, taskPlace=13, taskMember=33, taskContent=2024-05-30, isCompleted=true),
            Task(tno=4, taskTitle=ㅁㄴㅇㄹ, taskDate=ㅁㄹㅇㅁ, taskTime=aaa, taskPlace=13, taskMember=33, taskContent=2024-05-31, isCompleted=true),
            Task(tno=6, taskTitle=ㅠㅁ, taskDate=ㅁㄹㅇㅁ, taskTime=aaa, taskPlace=13, taskMember=33, taskContent=2024-06-30, isCompleted=true),
            Task(tno=8, taskTitle=ㅠㅁㅊ, taskDate=ㅁㄹㅇㅁ, taskTime=aaa, taskPlace=13, taskMember=33, taskContent=2024-06-31, isCompleted=true)],
        interests=[Interests(ino=2, interestName=리눅스, 도커)])
     */

    //     <참가중 단건>
    //     WHERE email = ?
    //     AND is_accpeted = 1
    //     AND sno = ?
    @Test
    public void findByEmailAndIsAcceptedAndSno() {
        // Given
        String email = "test1"; // 테스트할 member
        Long sno = 1L;

        // When
        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberStudyByEmailAndIsAcceptedAndSno(email, sno);

        //Then
        log.info(memberStudyList);
    }
    /*
    [MemberStudy(msNo=1, member=Member(mno=1, email=test1, nickname=1, memberProfileImage=null, memberDescription=1,
        interests=[Interests(ino=3, interestName=ㅗㅗㅗㅗ)]),
        study=Study(sno=1, studyName=ddddd, studyDescription=dd, studyPlan=ddd, maxMember=5, firstDate=2024-05-31, rStartDate=2024-05-01, rEndDate=2024-05-20,
        category=Category(cno=1, categoryName=백엔드, categoryImage=222), dDay=D-6,
        tasks=[Task(tno=1, taskTitle=1, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-05-13, isCompleted=false),
            Task(tno=3, taskTitle=ㅁ, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-05-15, isCompleted=false), Task(tno=5, taskTitle=ㅊㅍㅁ, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-06-13, isCompleted=false),
            Task(tno=7, taskTitle=ㅠㅁ츄, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-06-15, isCompleted=false)],
        interests=[Interests(ino=1, interestName=자바, 리눅스 ), Interests(ino=3, interestName=ㅗㅗㅗㅗ)]),
        isAccepted=true, isManaged=false, comment=ㅇㄴㅇ)]
     */



    // SELECT ms.eamil, ms.sno, m.nickname, m.member_profile_image
    // email = ? AND sno = ? AND is_managed = 1; 방장 관리 페이지
    @Test
    public void findMemberStudyByEmailAndSnoAndIsManagedTest() {
        // Given
        String email = "test1"; // 테스트할 이메일
        Long sno = 1L; // 테스트할 스터디 번호

        // When
        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberStudyByEmailAndSnoAndIsManaged(email, sno, false);

        // Then
        log.info(memberStudyList);
    }
    /*

     */






    // (참가 신청 보냈지만 아직 승인되지 않은 신청자 all)
    // sno = ? AND is_accepted = 0; 방장 페이지 -> 참가 신청 중 다건 조회
    @Test
    public void findMemberBySnoAndAccptYetTest() {
        // Given
        Long sno = 1L; // 테스트할 스터디 번호

        // When
        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberBySnoAndAccptYet(sno, false);

        // Then
        log.info(memberStudyList);
    }
    /*
    [MemberStudy(msNo=1, member=Member(mno=1, email=test1, nickname=1, memberProfileImage=null, memberDescription=1, interests=[Interests(ino=3, interestName=ㅗㅗㅗㅗ)]),
    study=Study(sno=1, studyName=ddddd, studyDescription=dd, studyPlan=ddd, maxMember=5, firstDate=2024-05-31, rStartDate=2024-05-01, rEndDate=2024-05-20,
    category=Category(cno=1, categoryName=백엔드, categoryImage=222), dDay=null,
    tasks=[Task(tno=1, taskTitle=1, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-05-13, isCompleted=false),
        Task(tno=3, taskTitle=ㅁ, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-05-15, isCompleted=false),
        Task(tno=5, taskTitle=ㅊㅍㅁ, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-06-13, isCompleted=false),
        Task(tno=7, taskTitle=ㅠㅁ츄, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-06-15, isCompleted=false)],
    interests=[Interests(ino=1, interestName=자바, 리눅스 ), Interests(ino=3, interestName=ㅗㅗㅗㅗ)]), isAccepted=false, isManaged=false, comment=ㅇㄴㅇ),

    MemberStudy(msNo=3, member=Member(mno=3, email=test3, nickname=3, memberProfileImage=null, memberDescription=3, interests=[]),
    study=Study(sno=1, studyName=ddddd, studyDescription=dd, studyPlan=ddd, maxMember=5, firstDate=2024-05-31, rStartDate=2024-05-01, rEndDate=2024-05-20,
    category=Category(cno=1, categoryName=백엔드, categoryImage=222), dDay=null,
    tasks=[Task(tno=1, taskTitle=1, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-05-13, isCompleted=false),
        Task(tno=3, taskTitle=ㅁ, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-05-15, isCompleted=false),
        Task(tno=5, taskTitle=ㅊㅍㅁ, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-06-13, isCompleted=false),
        Task(tno=7, taskTitle=ㅠㅁ츄, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-06-15, isCompleted=false)],
    interests=[Interests(ino=1, interestName=자바, 리눅스 ), Interests(ino=3, interestName=ㅗㅗㅗㅗ)]), isAccepted=false, isManaged=false, comment=ddd)]
    */





    // sno = ? AND is_accepted = 1; 방장 페이지 -> 참가 중인 멤버 조회 (강퇴)
    @Test
    public void findMemberBySnoAndIsAcceptedTest() {
        // Given
        Long sno = 1L; // 테스트할 스터디 번호

        // When
        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberBySnoAndIsAccepted(sno, true);

        // Then
        log.info(memberStudyList);
    }
/*
    [MemberStudy(msNo=2,
        member=Member(mno=2, email=test2, nickname=2, memberProfileImage=null, memberDescription=222, interests=[Interests(ino=4, interestName=ㅠㅠㅠㅠㅠ)]),
        study=Study(sno=1, studyName=ddddd, studyDescription=dd, studyPlan=ddd, maxMember=5, firstDate=2024-05-31, rStartDate=2024-05-01, rEndDate=2024-05-20, category=Category(cno=1, categoryName=백엔드, categoryImage=222), dDay=null,
        tasks=[Task(tno=1, taskTitle=1, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-05-13, isCompleted=false),
            Task(tno=3, taskTitle=ㅁ, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-05-15, isCompleted=false),
            Task(tno=5, taskTitle=ㅊㅍㅁ, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-06-13, isCompleted=false),
            Task(tno=7, taskTitle=ㅠㅁ츄, taskDate=333, taskTime=3134, taskPlace=13, taskMember=1, taskContent=2024-06-15, isCompleted=false)],
        interests=[Interests(ino=1, interestName=자바, 리눅스 ),
        Interests(ino=3, interestName=ㅗㅗㅗㅗ)]), isAccepted=true, isManaged=false, comment=ddd)]
 */



    // UPDATE member_study SET is_accepted = 1 WHERE email = ? AND sno = ?
    @Test
    public void updateIsAcceptedByEmailAndSnoTest() {
        // Given
        String email = "test1"; // 테스트할 이메일
        Long sno = 1L; // 테스트할 스터디 번호

        // When
        memberStudyRepository.updateIsAcceptedByEmailAndSno(email, sno);
    }
    /*
       update
        member_study
    set
        is_accepted=?
    where
        email=?
        and sno=?
    2024-05-14T03:22:42.152+09:00  INFO 33563 --- [otl] [    Test worker] c.o.o.r.q.i.StudyRepositoryCustomImpl    : Updated 1 rows
     */

}
//package com.otl.otl.repository;
//
//import com.otl.otl.domain.MemberStudy;
//import jakarta.transaction.Transactional;
//import lombok.extern.log4j.Log4j2;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//@Log4j2
//@SpringBootTest
//@Transactional
//public class MemberStudyRepositoryTests {
//
//    @Autowired
//    private MemberStudyRepository memberStudyRepository;
//
//    @Test
//    public void findSnoByEmailAndIsAccepted1() {
//        // Given
//        String email = "wer2587@naver.com"; // 테스트할 member
//
//        // When
//        List<Long> memberStudyList = memberStudyRepository.findSnoByEmailAndIsAccepted(email);
//
//        //Then
//        for (Long sno : memberStudyList) {
//            System.out.println("sno=" + sno);
//
//        }
//    }
//
//    @Test
//    public void findMemberStudyByIsAccepted(){
//        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberStudyByIsAccepted();
//
//        log.info(memberStudyList);
//    }
//
//
//    @Test
//    public void findSnoByEmailAndIsAccepted2() {
//        // Given
//        String email = "wer2587@naver.com"; // 테스트할 member
//
//        // When
//        List<Long> memberStudyList = memberStudyRepository.findSnoByEmailAndIsAccepted(email);
//
//        //Then
//        for (Long sno : memberStudyList) {
//            System.out.println("sno=" + sno);
//
//        }
//    }
//
//
//    //     WHERE email = ?
//    //     AND is_accpeted = 1
//    @Test
//    public void findByEmailAndIsAccepted() {
//        // Given
//        String email = "wer2587@naver.com"; // 테스트할 member
//
//        // When
//        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberStudyByEmailAndIsAccepted(email);
//
//        //Then
//        log.info(memberStudyList);
//    }
//
//
//    //     WHERE email = ?
//    //     AND is_accpeted = 1
//    //     AND sno = ?
//    @Test
//    public void findByEmailAndIsAcceptedAndSno() {
//        // Given
//        String email = "wer2587@naver.com"; // 테스트할 member
//        Long sno = 2L;
//
//        // When
//        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberStudyByEmailAndIsAcceptedAndSno(email, sno);
//
//        //Then
//        log.info(memberStudyList);
//    }
//
//}

