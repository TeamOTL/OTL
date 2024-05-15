package com.otl.otl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("sno")
    private Long sno;
    @JsonProperty("studyName")//스터디 고유번호
    private String studyName;
    @JsonProperty("studyDescription")//스터디 이름
    private String studyDescription;

    @JsonProperty("studyPlan")
    private String studyPlan;               //스터디 일정 (매주 월, 목)

    @JsonProperty("maxMember")
    private Long maxMember;                //최대 참가 인원

    @JsonProperty("firstDate")
    private String firstDate;                //스터디 시작일
    @JsonProperty("rStartDate")
    private String rStartDate ;                  // 모집 기간_시작일

    @JsonProperty("rEndDate")
    private String rEndDate ;                  // 모집 기간_종료일

    @JsonProperty("dDay")
    private String dDay;                       //dday

    @JsonProperty("cno")
    private Long cno;

    @JsonProperty("interestsDTO")
    private List<InterestsDTO> interestsDTO;
    @JsonProperty("taskDTO")
    private List<TaskDTO> taskDTO;

    // 새로운 필드 추가
    private List<String> memberNicknames; // 멤버 닉네임 리스트


}
