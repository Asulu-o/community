package org.example.community;

import org.example.community.entity.User;
import org.example.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test/db")
    public String testDatabase() {
        try {
            // 查询用户表中的所有数据
            User user = userMapper.selectById(1);
            if (user != null) {
                return "查询成功！用户：" + user;
            } else {
                return "暂无数据，请先插入用户数据";
            }
        } catch (Exception e) {
            return "数据库连接失败！错误信息：" + e.getMessage();
        }
    }
}