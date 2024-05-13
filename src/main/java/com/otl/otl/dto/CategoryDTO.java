package com.otl.otl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO {
    private Long cno;               // 카테고리 고유 번호
    private String categoryName;    // 카테고리 이름
    private String categoryImage;
}
