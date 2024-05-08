package com.otl.otl.domain;

import io.swagger.models.auth.In;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.java.Log;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sno;                       //스터디 고유번호

    @Column
    private String studyName;               //스터디 이름

    @Column
    private String studyDescription;        //스터디 소개

    @Column
    private String studyPlan;               //스터디 일정 (매주 월, 목)

    @Column
    private Long maxMember;                //최대 참가 인원

    @Column
    private String firstDate;                //스터디 시작일

    @Column
    private String rStartDate;                  // 모집 기간_시작일

    @Column
    private String rEndDate;                  // 모집 기간_종료일


    @OneToOne
    @JoinColumn(name = "cno")
    private Category category;


    @Transient
    private String dDay;



    //스터디 모집방 d-day
    public void calStudyDday() {
        LocalDate today = LocalDate.now();
        LocalDate rEndDateLocalDate = LocalDate.parse(rEndDate); // 모집 종료일을 LocalDate로 변환
        long daysDifference = ChronoUnit.DAYS.between(today, rEndDateLocalDate);

        this.dDay = "D-" + String.valueOf(daysDifference);

    }

    //나의 스터디 d+day
    public void calMyStudyDday() {
        LocalDate today = LocalDate.now();
        LocalDate firstDateLocalDate = LocalDate.parse(firstDate); // 스터디 시작일을 LocalDate로 변환
        long daysDifference = ChronoUnit.DAYS.between(today, firstDateLocalDate);

        this.dDay = "D+" + String.valueOf(Math.abs(daysDifference));

    }




}

