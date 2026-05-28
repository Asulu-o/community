package org.example.community.service;

import org.example.community.entity.Article;
import java.util.List;

public interface ArticleService {
    // 发布文章
    boolean publish(Long userId, String title, String content, Integer status);

    // 根据ID查询文章
    Article getById(Long id);

    // 查询用户的所有文章
    List<Article> getByUserId(Long userId);

    // 更新文章
    boolean update(Long id, String title, String content, Integer status);

    // 删除文章（逻辑删除）
    boolean delete(Long id);
}