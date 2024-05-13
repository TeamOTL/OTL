package com.otl.otl.dto;

import com.otl.otl.domain.Study;
import jakarta.persistence.*;

public class TaskDTO {
    private Long tno; // Task 고유 식발자
    private Study study; // study테이블 스터디 외래키
    private int taskWeek; // 스터디 회의록 주 차
    private String taskTitle; // 스터디 회의록 주제
    private String taskDate; // 스터디 회의록 날짜
    private String taskTime; // 스터디 회의록 시간
    private String taskPlace; // 스터디 회의록 장소
    private String taskMember; // 스터디 회의록 참석자
    private String taskContent; // 스터디 회의록 내용
    private boolean isCompleted; // 스더티 회의록 주 차완료 여부
    private String planDate; // 스터디 예정일
}
