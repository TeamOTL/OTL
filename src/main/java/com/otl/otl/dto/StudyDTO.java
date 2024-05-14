package com.otl.otl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudyDTO {

    private Long sno;                       //스터디 고유번호
    private String studyName;               //스터디 이름
    private String studyDescription;        //스터디 소개
    private String studyPlan;               //스터디 일정 (매주 월, 목)
    private Long maxMember;                //최대 참가 인원
    private String firstDate;                //스터디 시작일
    private String rStartDate;                  // 모집 기간_시작일
    private String rEndDate;                  // 모집 기간_종료일
    private String dDay;                       //dday
    private Long cno;

    private List<InterestsDTO> interestsDTO;
    private List<TaskDTO> taskDTO;

    // 새로운 필드 추가
    private List<String> memberNicknames; // 멤버 닉네임 리스트


}
