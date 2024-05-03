package com.otl.otl.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudyLog {             //해당일 학습 일지(회의록)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lno;

    @Column(nullable = false)
    private String logTitle;            // 일지 제목

    @Column
    private String logContent;          // 일지 내용

    @Column
    private String logMember;            // 해당일 참가자

    @Column
    private String logDate;             // 학습 날짜 (날짜 입력 폼으로 받고 문자열 저장)

    @Column
    private String logTime;             // 학습 시간

    @ManyToOne
    @JoinColumn(name = "fno")
    private LogFeedback logFeedback;    // 피드백 고유번호

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private Member member;              // 작성자

    @OneToOne
    @JoinColumn(name = "tno")
    private WeeklyTask weeklyTask;      // 학습 계획 고유번호

}
