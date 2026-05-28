package org.example.community.controller;

import org.example.community.common.Result;
import org.example.community.dto.ArticleDto;
import org.example.community.entity.Article;
import org.example.community.service.ArticleService;
import org.example.community.service.UserService;
import org.example.community.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    private Long getUserIdFromToken(String token) {
        String jwt = token.replace("Bearer ", "");
        String username = jwtUtil.getUsernameFromToken(jwt);
        return userService.getUserIdByUsername(username);
    }

    @PostMapping("/publish")
    public Result<?> publish(@RequestHeader("Authorization") String token,
                             @RequestBody ArticleDto articleDto) {

        Long userId = getUserIdFromToken(token);

        if (userId == null) {
            return Result.error("用户不存在");
        }

        if (articleDto.getTitle() == null || articleDto.getTitle().isEmpty()) {
            return Result.error("标题不能为空");
        }

        boolean success = articleService.publish(userId, articleDto.getTitle(),
                articleDto.getContent(), articleDto.getStatus());

        if (success) {
            return Result.success("发布成功", null);
        } else {
            return Result.error("发布失败");
        }
    }

    @GetMapping("/my-list")
    public Result<?> getMyArticles(@RequestHeader("Authorization") String token) {

        Long userId = getUserIdFromToken(token);

        if (userId == null) {
            return Result.error("用户不存在");
        }

        List<Article> articles = articleService.getByUserId(userId);
        return Result.success(articles);
    }

    @GetMapping("/{id}")
    public Result<?> getArticleById(@PathVariable Long id) {

        Article article = articleService.getById(id);
        if (article != null && article.getIsDeleted() == 0) {
            return Result.success(article);
        } else {
            return Result.error("文章不存在");
        }
    }

    @PutMapping("/{id}")
    public Result<?> updateArticle(@PathVariable Long id,
                                   @RequestBody ArticleDto articleDto) {

        boolean success = articleService.update(id, articleDto.getTitle(),
                articleDto.getContent(), articleDto.getStatus());

        if (success) {
            return Result.success("更新成功", null);
        } else {
            return Result.error("更新失败");
        }
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteArticle(@PathVariable Long id) {

        boolean success = articleService.delete(id);
        if (success) {
            return Result.success("删除成功", null);
        } else {
            return Result.error("删除失败");
        }
    }
}