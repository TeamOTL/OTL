package com.otl.otl.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WeeklyTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;                   //학습 계획 고유 번호

    @Column
    private String taskContent;         //계획 내용

    @Column
    private String taskDate;            //예정 날짜

//    @Column
//    private boolean taskIsDone;       //학습 계획 완료 여부

}
