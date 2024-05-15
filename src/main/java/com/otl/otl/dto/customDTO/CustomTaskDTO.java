package com.otl.otl.dto.customDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// CustomTaskDTO 클래스: taskDate와 taskTitle 필드만 포함하는 DTO
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomTaskDTO {

    @JsonProperty("taskDate")
    private String taskDate; // Task 날짜

    @JsonProperty("taskTitle")
    private String taskTitle; // Task 제목
}