package com.otl.otl.dto.customDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// CustomInterestDTO 클래스: interestName 필드만 포함하는 DTO
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomInterestDTO {

    @JsonProperty("interestName")
    private String interestName; // 관심사 이름
}