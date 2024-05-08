package com.otl.otl.dto;


import lombok.*;

@Data
public class BoardDTO {
    private Long bno;           // 게시물 고유번호
    private String boardTitle;      // 게시물 제목
    private String boardContent;    // 게시물 내용
    private String email;     // 게시물 작성자의 이메일
    private boolean isDeleted;      // 삭제 여부

    // 생성자, 게터, 세터, toString 등은 Lombok의 어노테이션을 사용하여 자동으로 생성됩니다.
}