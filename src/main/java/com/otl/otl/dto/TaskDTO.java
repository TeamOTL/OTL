package com.otl.otl.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskDTO {
    private Long tno;                //  Task 고유 식발자
    private String taskDate;        // 스터디 주차별 날짜
    private String taskTitle;       // 스터디 주차별 주제
    private String taskTime;        // 스터디 주차별 시간
    private String taskPlace;       // 스터디 회의록 - 스터디 장소
    private String taskMember;      // 스터디 회의록 - 참석자
    private String taskContent;     // 스터디 회의록 - 내용
    private boolean isCompleted;    // 스더티 회의록 - 스터디 완료 여부

}
