package com.otl.otl.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Log {             //해당일 학습 일지(회의록)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lno;

    @Column
    private String logContent;          // 일지 내용

    @Column
    private String logMember;            // 해당일 참가자

//    @Column                              // task_date 참조해서 log도 스터디날짜 사용
//    private String logDate;             // 학습 날짜 (날짜 입력 폼으로 받고 문자열 저장)

    @Column
    private String logTime;             // 학습 시간

    @Column
    private String logPlace;

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private Member member;              // 작성자

    @OneToOne
    @JoinColumn(name = "tno")              // task에서 date 당겨와서 사용
    private Task task;      // 학습 계획 고유번호

}
