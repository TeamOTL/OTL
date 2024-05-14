package com.otl.otl.repository.querydsl.impl;

import com.otl.otl.domain.*;
import com.otl.otl.dto.MemberStudyProjection.MemberStudyProjection;
import com.otl.otl.dto.MemberStudyProjection.MemberStudyProjectionImpl;
import com.otl.otl.repository.querydsl.StudyRepositoryCustom;
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
        QStudy qStudy = QStudy.study;

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




    //      WHERE rEndDate.loe(curDate)
    //      모집 중인 스터디 다건 조회
    //      현재 날짜와 모집 종료일 비교 (종료일 이전 스터디 반환)
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
    public List<MemberStudy> findMemberBySnoAndIsAccepted(Long sno, Boolean isAccepted) {
        QMemberStudy qMemberStudy = QMemberStudy.memberStudy;

        // QueryDSL을 사용하여 쿼리 작성
        List<MemberStudy> memberStudyList = from(qMemberStudy)
                .select(qMemberStudy)
                .where(qMemberStudy.study.sno.eq(sno)
                        .and(qMemberStudy.isAccepted.eq(true)))
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
    //     update
    //        member_study
    //    set
    //        is_accepted=?
    //    where
    //        email=?
    //        and sno=?
    //2024-05-14T03:22:42.152+09:00  INFO 33563 --- [otl] [    Test worker] c.o.o.r.q.i.StudyRepositoryCustomImpl    : Updated 1 rows

}