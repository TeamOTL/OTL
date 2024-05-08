package com.otl.otl.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tno; // Task 고유 식발자

    @ManyToOne
    @JoinColumn(name = "sno")
    private Study study; // study테이블 스터디 외래키

    @Column
    private int taskWeek; // 스터디 회의록 주 차

    @Column
    private String taskTitle; // 스터디 회의록 주제

    @Column
    private String taskDate; // 스터디 회의록 날짜

    @Column
    private String taskTime; // 스터디 회의록 시간

    @Column
    private String taskPlace; // 스터디 회의록 장소

    @Column
    private String taskMember; // 스터디 회의록 참석자

    @Column
    private String taskContent; // 스터디 회의록 내용

    @Column
    private boolean isCompleted; // 스더티 회의록 주 차완료 여부

    @Column
    private String planDate; // 스터디 예정일



}
