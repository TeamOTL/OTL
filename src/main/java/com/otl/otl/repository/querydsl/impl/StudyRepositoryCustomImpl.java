package com.otl.otl.repository.querydsl.impl;

import com.otl.otl.domain.*;
import com.otl.otl.dto.MemberStudyProjection.MemberStudyProjectionImpl;
import com.otl.otl.repository.querydsl.StudyRepositoryCustom;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Log4j2
public class StudyRepositoryCustomImpl extends QuerydslRepositorySupport implements StudyRepositoryCustom {


    public StudyRepositoryCustomImpl() {
        super(MemberStudy.class);
    }


    //     <참가중 다건>
    //     WHERE email = ?
    //     AND is_accpeted = 1
    @Override
    public List<MemberStudy> findMemberStudyByEmailAndIsAccepted(String email) {
        QMemberStudy qMemberStudy = QMemberStudy.memberStudy;
        QMember qMember = QMember.member;
        QStudy qStudy = QStudy.study;

        List<MemberStudy> memberStudyList = from(qMemberStudy)
                .select(Projections.constructor(MemberStudy.class, qMemberStudy.msNo, qMemberStudy.study, qMemberStudy.isAccepted, qMemberStudy.isManaged, qMemberStudy.comment))
                .where(qMemberStudy.member.email.eq(email)
                        .and(qMemberStudy.isAccepted.eq(true)))
                .fetch();

        for (MemberStudy memberStudy : memberStudyList) {
            Study study = memberStudy.getStudy();
            String dDay = study.calCombinedDday();
            study.setDDay(dDay);
            log.info(dDay);
        }

        return memberStudyList;
    }

    @Override
    public List<MemberStudyProjectionImpl> findMemberStudyByEmailAndIsAcceptedProjection(String email) {
        QMemberStudy qMemberStudy = QMemberStudy.memberStudy;

        List<MemberStudyProjectionImpl> memberStudyList = from(qMemberStudy)
                .select(Projections.constructor(MemberStudyProjectionImpl.class,
                        qMemberStudy.msNo,
                        qMemberStudy.study,
                        qMemberStudy.member.email, // Member 엔티티의 email 속성
                        qMemberStudy.isAccepted,
                        qMemberStudy.isManaged,
                        qMemberStudy.comment))
                .where(qMemberStudy.member.email.eq(email)
                        .and(qMemberStudy.isAccepted.eq(true)))
                .fetch();

        for (MemberStudyProjectionImpl memberStudy : memberStudyList) {
            Study study = memberStudy.getStudy();
            String dDay = study.calCombinedDday();
            study.setDDay(dDay);
            log.info(dDay);
        }

        return memberStudyList;
    }


    //     <참가중 단건>
    //     WHERE email = ?
    //     AND is_accpeted = 1
    //     AND sno = ?
    @Override
    public List<MemberStudy> findMemberStudyByEmailAndIsAcceptedAndSno(String email, Long sno) {
        QMemberStudy qMemberStudy = QMemberStudy.memberStudy;
        QMember qMember = QMember.member;
        QStudy qStudy = QStudy.study;


        List<MemberStudy> memberStudyList = from(qMemberStudy)
                .where(qMemberStudy.member.email.eq(email)
                        .and(qMemberStudy.isAccepted.eq(true))
                        .and(qMemberStudy.study.sno.eq(sno)))
                .fetch();


        for (MemberStudy memberStudy : memberStudyList) {
            Study study = memberStudy.getStudy();
            String dDay = study.calCombinedDday();
            study.setDDay(dDay);
            log.info(dDay);
        }

        return memberStudyList;
    }

    public List<MemberStudyProjectionImpl> findMemberStudyByEmailAndIsAcceptedAndSnoProjection(String email, Long sno) {
        QMemberStudy qMemberStudy = QMemberStudy.memberStudy;

        List<MemberStudyProjectionImpl> memberStudyList = from(qMemberStudy)
                .select(Projections.constructor(MemberStudyProjectionImpl.class,
                        qMemberStudy.msNo,
                        qMemberStudy.isAccepted,
                        qMemberStudy.isManaged,
                        qMemberStudy.comment))
                .where(qMemberStudy.member.email.eq(email)
                        .and(qMemberStudy.isAccepted.eq(true))
                        .and(qMemberStudy.study.sno.eq(sno)))
                .fetch();

        for (MemberStudyProjectionImpl memberStudy : memberStudyList) {
            Study study = memberStudy.getStudy();
            String dDay = study.calCombinedDday();
            study.setDDay(dDay);
            log.info(dDay);
        }
        return memberStudyList;
    }

//    @Override
//    public List<MemberStudy> findMemberStudyByEmailAndIsAcceptedAndSnoCount(String email, Long sno) {
//        QMemberStudy qMemberStudy = QMemberStudy.memberStudy;
//        QStudy qStudy = QStudy.study;
//
//        List<Tuple> results = from(qMemberStudy)
//                .select(qMemberStudy, qStudy.count())
//                .where(qMemberStudy.member.email.eq(email)
//                        .and(qMemberStudy.isAccepted.eq(true))
//                        .and(qMemberStudy.study.sno.eq(sno)))
//                .groupBy(qMemberStudy.msNo) // GROUP BY 절 추가
//                .fetch();
//
//        List<MemberStudy> memberStudyList = new ArrayList<>();
//
//        for (Tuple tuple : results) {
//            MemberStudy memberStudy = tuple.get(qMemberStudy);
//            Long count = tuple.get(qStudy.count());
//            memberStudy.setPeople(count.longValue()); // 참가인원 설정
//            memberStudyList.add(memberStudy);
//        }
//
//        for (MemberStudy memberStudy : memberStudyList) {
//            Study study = memberStudy.getStudy();
//            String dDay = study.calCombinedDday();
//            study.setDDay(dDay);
//            log.info(dDay);
//        }
//
//        return memberStudyList;
//    }


    //
    @Override
    public List<Tuple> findAllAddCount() {
        QMemberStudy qMemberStudy = QMemberStudy.memberStudy;

        List<Tuple> results = from(qMemberStudy)
                .select(qMemberStudy.study.sno, qMemberStudy.study.count(), qMemberStudy.isAccepted.count())
                .groupBy(qMemberStudy.study.sno)
                .fetch();

        return results;
    }


    // SELECT ms.eamil, ms.sno, m.nickname, m.member_profile_image
    // email = ? AND sno = ? AND is_managed = 1; 방장 관리 페이지
    @Override
    public List<MemberStudy> findMemberStudyByEmailAndSnoAndIsManaged(String email, Long sno, Boolean isManaged) {
        QMemberStudy qMemberStudy = QMemberStudy.memberStudy;

        // QueryDSL을 사용하여 쿼리 작성
        List<MemberStudy> memberStudyList = from(qMemberStudy)
                .select(qMemberStudy)
                .leftJoin(qMemberStudy.member).fetchJoin()
                .leftJoin(qMemberStudy.study).fetchJoin()
                .where(qMemberStudy.study.sno.eq(sno)
                        .and(qMemberStudy.isAccepted.eq(false)))
                .fetch();

        return memberStudyList;
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


    // 방장/참가 신청 승인거절
    // member : memberProfileImage, nickname, interestsName,msNo, comment
    // (참가 신청 보냈지만 아직 승인되지 않은 신청자 all)
// sno = ? AND is_accepted = 0; 방장 페이지 -> 참가 신청 중 다건 조회
/*
SELECT m.member_prpfile_imgae, m.nickname, i.interests_name, ms.comment
FROM member_study ms
WHERE sno = ?
AND is_accepted = 0;
 */
    @Override
    public List<MemberStudy> findMemberBySnoAndAccptYet(Long sno, Boolean isAccepted) {
        QMemberStudy qMemberStudy = QMemberStudy.memberStudy;

        // QueryDSL을 사용하여 쿼리 작성
        List<MemberStudy> memberStudyList = from(qMemberStudy)
                .select(qMemberStudy)
                .where(qMemberStudy.study.sno.eq(sno)
                        .and(qMemberStudy.isAccepted.eq(false)))
                .fetch();

        return memberStudyList;
    }


    // sno = ? AND is_accpeted = 1; 방장 페이지 -> 참가 중인 멤버 조회 (강퇴)
    /*
    SELECT ms.emil, ms.sno, m.member_profile_image, m.nickname
    FROM member_study ms
    WHERE sno = ?,
    AND is_accepted = 1;
     */
    @Override
    public List<MemberStudy> findMemberBySnoAndIsAccepted(Long sno, Boolean isAccepted, Boolean isManaged) {
        QMemberStudy qMemberStudy = QMemberStudy.memberStudy;

        // QueryDSL을 사용하여 쿼리 작성
        List<MemberStudy> memberStudyList = from(qMemberStudy)
                .select(qMemberStudy)
                .where(qMemberStudy.study.sno.eq(sno)
                        .and(qMemberStudy.isAccepted.eq(true))
                        .and(qMemberStudy.isManaged.eq(false)))
                .fetch();

        return memberStudyList;
    }

    // sno = ? AND is_accpeted = 0; 방장 페이지 -> 참가 대기 멤버 조회 (강퇴)
    /*
    SELECT ms.emil, ms.sno, m.member_profile_image, m.nickname
    FROM member_study ms
    WHERE sno = ?,
    AND is_accepted = 0;
     */
    @Override
    public List<MemberStudy> findMemberBySnoAndAcceptedFalse(Long sno, Boolean isAccepted, Boolean isManaged) {
        QMemberStudy qMemberStudy = QMemberStudy.memberStudy;

        // QueryDSL을 사용하여 쿼리 작성
        List<MemberStudy> memberStudyList = from(qMemberStudy)
                .select(qMemberStudy)
                .where(qMemberStudy.study.sno.eq(sno)
                        .and(qMemberStudy.isAccepted.eq(false))
                        .and(qMemberStudy.isManaged.eq(false)))
                .fetch();

        return memberStudyList;
    }


    // UPDATE member_study SET is_accepted = 1 WHERE email = ? AND sno = ?
    @Override
    @Transactional
    public void updateIsAcceptedByEmailAndSno(String email, Long sno) {
        QMemberStudy qMemberStudy = QMemberStudy.memberStudy;


        // QueryDSL을 사용하여 업데이트 쿼리 작성
        long updatedCount = update(qMemberStudy)
                .set(qMemberStudy.isAccepted, true)
                .where(qMemberStudy.member.email.eq(email)
                        .and(qMemberStudy.study.sno.eq(sno)))
                .execute();

        // 업데이트된 행 수 확인
        log.info("Updated {} rows", updatedCount);
    }

    // UPDATE member_study SET is_accepted = 0 WHERE email = ? AND sno = ?
    @Override
    @Transactional
    public void updateIsAcceptedRefuseByEmailAndSno(String email, Long sno) {
        QMemberStudy qMemberStudy = QMemberStudy.memberStudy;


        // QueryDSL을 사용하여 업데이트 쿼리 작성
        long updatedCount = update(qMemberStudy)
                .set(qMemberStudy.isAccepted, false)
                .where(qMemberStudy.member.email.eq(email)
                        .and(qMemberStudy.study.sno.eq(sno)))
                .execute();

        // 업데이트된 행 수 확인
        log.info("Updated {} rows", updatedCount);
    }
    //     update
    //        member_study
    //    set
    //        is_accepted=?
    //    where
    //        email=?
    //        and sno=?
    //2024-05-14T03:22:42.152+09:00  INFO 33563 --- [otl] [    Test worker] c.o.o.r.q.i.StudyRepositoryCustomImpl    : Updated 1 rows


    // <-----------------------------------------My Study ------------------------------------------------->


// <----------------------------------------- Study ------------------------------------------------->
    //  <<< 모집 페이지 >>>

    /*   ( 2024-05-20 -> d-13 )
        - 모집 중인 스터디 다건 조회 (모집 종료일이 지나지 않은)
     */
    @Override
    public List<Study> findAllByCurDate() {
        QStudy qStudy = QStudy.study;
        LocalDate curDate = LocalDate.now();

        String curDateString = curDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<Study> studyList = from(qStudy)
                .where(Expressions.stringTemplate("STR_TO_DATE({0}, '%Y-%m-%d')", qStudy.rEndDate).gt(curDateString))
                .fetch();

        return studyList;
    }


    /*   ( WHERE category.cno = ?; 카테고리 드룹다운 선택 submit )
        - 모집 중인 스터디 검색 다건 조회 [카테고리 검색]
     */
    @Override
    public List<Study> findAllByCurDateByCno(Long cno) {
        QStudy qStudy = QStudy.study;
        LocalDate curDate = LocalDate.now();

        String curDateString = curDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<Study> studyList = from(qStudy)
                .where(qStudy.category.cno.eq(cno)
                        .and(Expressions.stringTemplate("STR_TO_DATE({0}, '%Y-%m-%d')", qStudy.rEndDate).gt(curDateString)))
                .fetch();

        return studyList;
    }


    /*  LIKE "%?%"
            - [키워드 검색] 모집 중인 스터디 다건 조회
        */
    @Override
    public List<Study> findAllByCurDateByKeyword(String keyword) {
        QStudy qStudy = QStudy.study;
        LocalDate curDate = LocalDate.now();

        String curDateString = curDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<Study> studyList = from(qStudy)
                .where(qStudy.studyName.like("%" + keyword + "%") // 문자열 키워드 검색 조건 추가
                        .and(Expressions.stringTemplate("STR_TO_DATE({0}, '%Y-%m-%d')", qStudy.rEndDate).gt(curDateString)))
                .fetch();

        return studyList;
    }

//    @Override
//    public List<Study> findAllByCurDateByCno(Long cno, String keyword) {
//        QStudy qStudy = QStudy.study;
//        LocalDate curDate = LocalDate.now();
//
//        String curDateString = curDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//        BooleanExpression keywordExpression = qStudy.studyName.containsIgnoreCase(keyword); // 키워드가 스터디명에 포함되는 경우
//
//        List<Study> studyList = from(qStudy)
//                .where(qStudy.category.cno.eq(cno)
//                        .and(Expressions.stringTemplate("STR_TO_DATE({0}, '%Y-%m-%d')", qStudy.rEndDate).gt(curDateString))
//                        .and(keywordExpression)) // 키워드 검색 조건 추가
//                .fetch();
//
//        return studyList;
//    }

    /*  COUNT(is_accpeted = 1) GROUP BY sno;
            - [키워드 검색] 모집 중인 스터디 다건 조회
    */
    @Override
    public List<Tuple> countAcceptedBySno() {
        QMemberStudy qMemberStudy = QMemberStudy.memberStudy;

        List<Tuple> results = from(qMemberStudy)
                .select(qMemberStudy.study.sno, qMemberStudy.study.sno.count())
                .where(qMemberStudy.isAccepted.isTrue())
                .groupBy(qMemberStudy.study.sno)
                .fetch();

        return results;
    }
    /*
      select
        ms1_0.sno,
        count(ms1_0.sno)
    from
        member_study ms1_0
    where
        ms1_0.is_accepted=?
    group by
        ms1_0.sno
Hibernate:
    select
        ms1_0.sno,
        count(ms1_0.sno)
    from
        member_study ms1_0
    where
        ms1_0.is_accepted=?
    group by
        ms1_0.sno
2024-05-15T05:20:09.277+09:00  INFO 59638 --- [otl] [    Test worker] c.o.otl.repository.StudyRepositoryTests  : [[1, 3], [2, 2]]
     */



    // DELETE FROM member_study WHERE email = ? AND sno = ?
    @Override
    @Transactional
    public void deleteMemberStudyByEmailAndSno(String email, Long sno) {
        QMemberStudy qMemberStudy = QMemberStudy.memberStudy;

        long deleteCount = delete(qMemberStudy)
                .where(qMemberStudy.member.email.eq(email)
                        .and(qMemberStudy.study.sno.eq(sno))
                        .and(qMemberStudy.isAccepted.eq(false)))
                .execute();

        log.info("Deleted {} rows", deleteCount);
    }
}
