package com.otl.otl.domain;

import io.swagger.models.auth.In;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.java.Log;

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
    private String studyStartDate;          //스터디 시작일?

    @Column
    private Long max_member;                //최대 참가 인원

    @ManyToOne
    @JoinColumn(name = "ino")
    private Interests interests;            //관심 분야

    @Column
    private String period;                  // 모집 기간

}

