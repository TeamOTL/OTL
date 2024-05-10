package com.otl.otl.dto;

import com.otl.otl.domain.Member;
import lombok.*;
import lombok.extern.log4j.Log4j2;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Log4j2
public class TodolistDTO {
    private Long toNo;               // 투두리스트 고유번호
    private String todolistContent;  // 투두리스트 내용
    private boolean isCompleted;     // 투두리스트 완료 여부
    private String todoStartDate;    // 투두 시작 날짜
    private String todoEndDate;      // 투두 종료 날짜
    private boolean isDeleted;       // 투두리스트 삭제 여부
    private String memberEmail;      // 투두리스트 작성자 이메일

}
