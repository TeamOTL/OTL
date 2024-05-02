package com.otl.otl.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long bno;           // 게시물 고유번호

    @Column (nullable = false)
    private String boardTitle;       // 게시물 제목

    @Column (nullable = false)
    private String boardContent;     // 게시물 내용

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private Member member;          // 게시물 작성자

}
