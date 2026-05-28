package org.example.community.dto;

import lombok.Data;

@Data
public class ArticleDto {
    private String title;
    private String content;
    private Integer status;  // 0草稿 1待审 2已发布
}