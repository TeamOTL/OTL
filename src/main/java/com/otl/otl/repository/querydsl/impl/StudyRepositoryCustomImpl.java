package com.otl.otl.repository.querydsl.impl;

import com.otl.otl.domain.*;
import com.otl.otl.dto.StudyListDTO;
import com.otl.otl.repository.querydsl.StudyRepositoryCustom;
import com.querydsl.core.types.dsl.Expressions;
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

    @Override
    public List<Long> findSnoByEmailAndIsAccepted(String email) {
        QMemberStudy memberStudy = QMemberStudy.memberStudy;

        return from(memberStudy)
                .select(memberStudy.study.sno)
                .innerJoin(memberStudy.member, QMember.member)
                .on(memberStudy.member.email.eq(email))
                .where(memberStudy.isAccepted.eq(true))
                .fetch();
    }


    // AND is_accpeted = 1
    @Override
    public List<MemberStudy> findMemberStudyByIsAccepted() {
        QMemberStudy qMemberStudy = QMemberStudy.memberStudy;
        QMember qMember = QMember.member;
        QStudy qStudy = QStudy.study;

        List<MemberStudy> memberStudyList = from(qMemberStudy)
                .where(qMemberStudy.isAccepted.eq(true))
                .fetch();


        for (MemberStudy memberStudy : memberStudyList) {
            Study study = memberStudy.getStudy();
            String dDay = study.calCombinedDday();
            study.setDDay(dDay);
            log.info(dDay);
        }

        return memberStudyList;
    }



    // WHERE email = ?
    // AND is_accpeted = 1
    @Override
    public List<MemberStudy> findMemberStudyByEmailAndIsAccepted(String email) {
        QMemberStudy qMemberStudy = QMemberStudy.memberStudy;
        QMember qMember = QMember.member;
        QStudy qStudy = QStudy.study;

//        List<MemberStudy> memberStudyList = from(qMemberStudy)
//                .where(qMemberStudy.member.email.eq(email)
//                        .and(qMemberStudy.isAccepted.eq(true)))
//                .fetch();
        List<MemberStudy> memberStudyList = from(qMemberStudy)
                .innerJoin(qMemberStudy.study, qStudy)
                .fetchJoin() // Study와 함께 로딩
                .where(qMemberStudy.isAccepted.eq(true))
                .fetch();



        for (MemberStudy memberStudy : memberStudyList) {
            Study study = memberStudy.getStudy();
            String dDay = study.calCombinedDday();
            study.setDDay(dDay);
            log.info(dDay);
        }

        return memberStudyList;
    }


    // WHERE email = ?
    // AND is_accpeted = 1
    // AND sno = ? ;
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



//    @Override
//    public List<Study> findAllByCurDate() {
//        QStudy qStudy = QStudy.study;
//        LocalDate curDate = LocalDate.now();
//
//        String curDateString = curDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//
//        List<Study> studyList = from(qStudy)
//                .where(Expressions.stringTemplate("STR_TO_DATE({0}, '%Y-%m-%d')", qStudy.rEndDate).loe(curDateString))
//                .fetch();
//
//        return studyList;
//    }


    @Override
    public List<Study> findAllByCurDate() {
        QStudy qStudy = QStudy.study;
        LocalDate curDate = LocalDate.now();

        String curDateString = curDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<Study> studyList = from(qStudy)
                .where(Expressions.stringTemplate("STR_TO_DATE({0}, '%Y-%m-%d')", qStudy.rEndDate).loe(curDateString))
                .fetch();

        return studyList;
    }

    @Override
public List<StudyListDTO> findAllByCurDate2() {
    QStudy qStudy = QStudy.study;
    LocalDate curDate = LocalDate.now();
    String curDateString = curDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

//    List<Tuple> tupleList = from(qStudy)
//            .select(qStudy.category.name, qStudy.category.image, qStudy.interest.name,
//                    qStudy.member.id, qStudy.member.email, qStudy.member.nickname, qStudy.member.profileImage,
//                    qStudy.member.description, qStudy.id, qStudy.studyName, qStudy.studyDescription,
//                    qStudy.studyPlan, qStudy.maxMember, qStudy.firstDate, qStudy.rStartDate,
//                    qStudy.rEndDate, qStudy.dDay, qStudy.memberStudies.any().id,
//                    qStudy.memberStudies.any().accepted, qStudy.memberStudies.any().managed,
//                    qStudy.memberStudies.any().comment)
//            .leftJoin(qStudy.memberStudies).fetchJoin()
//            .where(Expressions.stringTemplate("STR_TO_DATE({0}, '%Y-%m-%d')", qStudy.rEndDate).loe(curDateString))
//            .fetch();
//
//    List<StudyListDTO> studyList = tupleList.stream().map(tuple -> StudyListDTO.builder()
//                    .categoryName(tuple.get(0, String.class))
//                    .categoryImage(tuple.get(1, String.class))
//                    .interestName(tuple.get(2, String.class))
//                    .mno(tuple.get(3, Long.class))
//                    .email(tuple.get(4, String.class))
//                    .nickname(tuple.get(5, String.class))
//                    .memberProfileImage(tuple.get(6, String.class))
//                    .memberDescription(tuple.get(7, String.class))
//                    .sno(tuple.get(8, Long.class))
//                    .studyName(tuple.get(9, String.class))
//                    .studyDescription(tuple.get(10, String.class))
//                    .studyPlan(tuple.get(11, String.class))
//                    .maxMember(tuple.get(12, Long.class))
//                    .firstDate(tuple.get(13, String.class))
//                    .rStartDate(tuple.get(14, String.class))
//                    .rEndDate(tuple.get(15, String.class))
//                    .dDay(tuple.get(16, String.class))
//                    .msNo(tuple.get(17, Long.class))
//                    .isAccepted(tuple.get(18, Boolean.class))
//                    .isManaged(tuple.get(19, Boolean.class))
//                    .comment(tuple.get(20, String.class))
//                    .build())
//            .collect(Collectors.toList());
//
//    return studyList;

    return null;
}







}