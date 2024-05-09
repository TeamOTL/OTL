package com.otl.otl.dto;


import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BoardDTO {
    private Long bno;           // 게시물 고유번호
    private String boardTitle;  // 게시물 제목
    private String boardContent;// 게시물 내용
    private String email;       // 게시물 작성자의 이메일
    private String nickname;    // 게시물 작성자의 닉네임
    private boolean isDeleted;  // 삭제 여부
    private LocalDateTime regDate;  // 등록 시간
    private LocalDateTime modDate;  // 수정된 시간

    // 생성자, 게터, 세터, toString 등은 Lombok의 어노테이션을 사용하여 자동으로 생성됩니다.
}