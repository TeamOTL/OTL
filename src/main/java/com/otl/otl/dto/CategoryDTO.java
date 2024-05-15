package com.otl.otl.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDTO {
    private Long cno;// 카테고리 고유 번호
    @JsonProperty("category_name")
    private String categoryName;    // 카테고리 이름
    @JsonProperty("category_image")
    private String categoryImage;
}
