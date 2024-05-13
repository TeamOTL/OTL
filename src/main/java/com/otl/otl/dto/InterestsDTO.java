package com.otl.otl.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InterestsDTO {

    private Long ino;               // 관심 분야 고유번호
    private String interestName;    // 관심 분야 이름

}
