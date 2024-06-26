package com.otl.otl.repository;

import com.otl.otl.domain.Member;
import com.otl.otl.domain.MemberStudy;
import com.otl.otl.domain.Study;
import com.otl.otl.domain.Task;
import com.otl.otl.dto.MemberStudyProjection.MemberStudyProjection;
import com.otl.otl.dto.MemberStudyProjection.MemberStudyProjectionImpl;
import com.otl.otl.service.StudyService;
import com.querydsl.core.Tuple;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

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
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TaskRepository taskRepository;


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
    public void findMemberStudyByEmailAndIsAcceptedProJectionMember() {
        // Given
        String email = "test1"; // 테스트할 member

        // When
        List<MemberStudyProjectionImpl> memberStudyList = memberStudyRepository.findMemberStudyByEmailAndIsAcceptedProjection(email);

        //Then
        log.info(memberStudyList);

    }

    /*
         [MemberStudyProjectionImpl(msNo=6, study=Study(sno=2, studyName=ㅁㄴㅇㄹ, studyDescription=2ㅇㄹㅁㅇㄴㄹㅁㄴㅇㄹ, studyPlan=ㅇㅇㅇ, maxMember=2, firstDate=2024-05-24, rStartDate=2024-05-05, rEndDate=2024-05-23,
             category=Category(cno=2, categoryName=프론트엔드, categoryImage=22), dDay=D-9,
         tasks=[Task(tno=2, taskDate=ㅁㄹㅇㅁ, taskTitle=ㄴㅁㅇㄹ, taskTime=aaa, taskPlace=13, taskMember=33, taskContent=2024-05-30, isCompleted=true),
            Task(tno=4, taskDate=ㅁㄹㅇㅁ, taskTitle=ㅁㄴㅇㄹ, taskTime=aaa, taskPlace=13, taskMember=33, taskContent=2024-05-31, isCompleted=true),
            Task(tno=6, taskDate=ㅁㄹㅇㅁ, taskTitle=ㅠㅁ, taskTime=aaa, taskPlace=13, taskMember=33, taskContent=2024-06-30, isCompleted=true),
            Task(tno=8, taskDate=ㅁㄹㅇㅁ, taskTitle=ㅠㅁㅊ, taskTime=aaa, taskPlace=13, taskMember=33, taskContent=2024-06-31, isCompleted=true)],
        interests=[Interests(ino=2, interestName=리눅스, 도커)]), email=test1, isAccepted=true, isManaged=false, comment=afdsfasdf)]
     */


    //projection 안돼요
    //     <참가중 단건>
    //     WHERE email = ?
    //     AND is_accpeted = 1
    //     AND sno = ?
    @Test
    public void findByEmailAndIsAcceptedAndSno() {
        // Given
        String email = "wer2587@naver.com"; // 테스트할 member
        Long sno = 1L;

        // When
        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberStudyByEmailAndIsAccepted(email);

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
    // 참가 단건 프로젝션 안 돼
    @Test
    public void findByEmailAndIsAcceptedAndSnoProjection() {
        // Given
        String email = "test1"; // 테스트할 member
        Long sno = 2L;

        // When
        List<MemberStudyProjectionImpl> memberStudyList = memberStudyRepository.findMemberStudyByEmailAndIsAcceptedAndSnoProjection(email, sno);

        //Then
        log.info(memberStudyList);
    }


    @Test
    public void findCount() {
        List<Tuple> memberStudyList = memberStudyRepository.findAllAddCount();

        log.info(memberStudyList);
    }
/*
    select
        ms1_0.sno,
        count(ms1_0.sno)
    from
        member_study ms1_0
    group by
        ms1_0.sno
Hibernate:
    select
        ms1_0.sno,
        count(ms1_0.sno)
    from
        member_study ms1_0
    group by
        ms1_0.sno
2024-05-14T16:28:26.905+09:00  INFO 43731 --- [otl] [    Test worker] c.o.o.r.MemberStudyRepositoryTests       : [[1, 3], [2, 3]]
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


    // 프로젝션 줘서 스터디쪽 끊어
    // (참가 신청 보냈지만 아직 승인되지 않은 신청자 all)
    // sno = ? AND is_accepted = 0; 방장 페이지 -> 참가 신청 중 다건 조회
//    @Test
//     void findMemberBySnoAndAccptYetTest() {
//        // Given
//        Long sno = 1L; // 테스트할 스터디 번호
//
//        // When
//        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberBySnoAndAccptYet(sno, false, false);
//
//        // Then
//        log.info(memberStudyList);
//    }
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


    // sno = ? AND is_accepted = 1 AND is_managed = 0; 방장 페이지 -> 참가 중인 멤버 조회 (강퇴)
    @Test
    public void findMemberBySnoAndIsAcceptedTest() {
        // Given
        Long sno = 2L; // 테스트할 스터디 번호

        // When
        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberBySnoAndIsAccepted(sno);

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


    @Test
    public void findMemberBySnoAndIsAcceptedTest2() {
        // Given
        Long sno = 1L; // 테스트할 스터디 번호

        // When
        List<MemberStudy> memberStudyList = memberStudyRepository.findMemberBySnoAndAcceptedYet(sno);

        // Then
        log.info(memberStudyList);
    }

    //update 성공 !!!
    @Test
    @Transactional
    @Commit
    public void updateIsAcceptedByEmailAndSnoTest() {
        // Given
        String email = "test1"; // 테스트할 이메일
        Long sno = 1L; // 테스트할 스터디 번호

        // 부모 테이블인 Study를 조회합니다.
        Study study = studyRepository.findById(sno)
                .orElseThrow(() -> new EntityNotFoundException("Study not found"));
        log.info("this:"+study);

        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        log.info("this:" + member);
        // MemberStudy 객체를 생성하고 필드를 설정합니다.
        MemberStudy memberStudy = MemberStudy.builder()
                .study(study) // 부모 테이블인 Study 설정
                .member(member)
                .build();

        // When
        memberStudyRepository.updateIsAcceptedByEmailAndSno(email, sno);
    }

    @Test
    @Transactional
    @Commit
    public void deleteMemberStudyByEmailAndSnoTest() {
        // Given
        String email = "test1"; // 테스트할 이메일
        Long sno = 7L; // 테스트할 스터디 번호

        // 부모 테이블인 Study를 조회합니다.
        Study study = studyRepository.findById(sno)
                .orElseThrow(() -> new EntityNotFoundException("Study not found"));
        log.info("this:"+study);

        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        log.info("this:" + member);
        // MemberStudy 객체를 생성하고 필드를 설정합니다.
        MemberStudy memberStudy = MemberStudy.builder()
                .study(study) // 부모 테이블인 Study 설정
                .member(member)
                .build();

        // When
        memberStudyRepository.deleteMemberStudyByEmailAndSno(email, sno);
    }
    @Test
    @Transactional
    @Commit
    public void deleteTaskTest(){
        Long tno = 3L;
        Long sno = 1L;
        Study study = studyRepository.findById(sno)
                .orElseThrow(() -> new EntityNotFoundException("Study not found"));
        log.info("this:"+study);

        Task task = Task.builder()
                .study(study)
                .build();


    taskRepository.deleteTask(sno, tno);
    }


    // UPDATE member_study SET is_accepted = 1 WHERE email = ? AND sno = ?
//    @Test
//    public void updateIsAcceptedByEmailAndSnoTest() {
//        // Given
//        String email = "test1"; // 테스트할 이메일
//        Long sno = 1L; // 테스트할 스터디 번호
//
//        Study study = studyRepository.findById(sno).orElseThrow(() -> new EntityNotFoundException("Study not found"));
//        MemberStudy memberStudy = new MemberStudy();
//        memberStudy.setStudy(study);
//
//        // When
//        memberStudyRepository.updateIsAcceptedByEmailAndSno(email, sno);
//    }
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
