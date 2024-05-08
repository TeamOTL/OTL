package com.otl.otl.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;               // 카테고리 고유 번호

    @Column
    private String categoryName;    // 카테고리 이름

    @Column
    private String categoryImage;   // 카테고리 이미지

}
