package org.example.community.service.impl;

import org.example.community.entity.Article;
import org.example.community.mapper.ArticleMapper;
import org.example.community.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public boolean publish(Long userId, String title, String content, Integer status) {
        Article article = new Article();
        article.setUserId(userId);
        article.setTitle(title);
        article.setContent(content);
        article.setStatus(status != null ? status : 0); // 默认为草稿
        article.setViewCount(0);

        int result = articleMapper.insert(article);
        return result > 0;
    }

    @Override
    public Article getById(Long id) {
        return articleMapper.selectById(id);
    }

    @Override
    public List<Article> getByUserId(Long userId) {
        return articleMapper.selectList(
                new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<Article>()
                        .eq("user_id", userId)
                        .orderByDesc("create_time")
        );
    }

    @Override
    public boolean update(Long id, String title, String content, Integer status) {
        Article article = new Article();
        article.setId(id);
        article.setTitle(title);
        article.setContent(content);
        article.setStatus(status);

        int result = articleMapper.updateById(article);
        return result > 0;
    }

    @Override
    public boolean delete(Long id) {
        int result = articleMapper.deleteById(id);
        return result > 0;
    }
}