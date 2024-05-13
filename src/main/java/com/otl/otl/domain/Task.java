package com.otl.otl.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "study")

public class Task  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno; // Task 고유 식발자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sno")
    private Study study; // study테이블 스터디 외래키

    @Column
    private String taskTitle; // 스터디 회의록 주제

    @Column
    private String taskDate; // 스터디 회의록 날짜 (캘린더 표시)

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





//    @Override
//    public int compareTo(Task other) {
//        return this.tno - other.tno;
//    }
//
//    public void changeStudy(Study study){
//        this.study = study;
//    }

}
