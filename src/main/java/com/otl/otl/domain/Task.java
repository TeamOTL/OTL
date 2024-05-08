package com.otl.otl.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;                   //학습 계획 고유 번호

    @Column
    private String taskContent;         //계획 내용

    @Column
    private String taskDate;            //스터디 날짜

    @Column
    private boolean isDone;             // 완료 여부

    @ManyToOne
    @JoinColumn(name = "sno")
    private Study study;          // 주차별 계획


}
