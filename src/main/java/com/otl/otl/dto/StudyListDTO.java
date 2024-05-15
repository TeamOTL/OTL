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
public class StudyListDTO {
    private Long sno;
    private String studyName;
    private String studyDescription;
    private String studyPlan;
    private Long maxMember;
    private String firstDate;
    private String rStartDate;
    private String rEndDate;
    private Long categoryCno;

    private String categoryName;
    private String categoryImage;

    private String dDay;
    private String people;

    private List<TaskDTO> tasks;
    private List<InterestsDTO> interests;

}
