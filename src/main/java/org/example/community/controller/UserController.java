package org.example.community.controller;

import org.example.community.common.Result;
import org.example.community.dto.LoginDto;
import org.example.community.dto.RegisterDto;
import org.example.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<?> register(@RequestBody RegisterDto registerDto) {

        if (registerDto.getUsername() == null || registerDto.getUsername().isEmpty()) {
            return Result.error("用户名不能为空");
        }
        if (registerDto.getPassword() == null || registerDto.getPassword().isEmpty()) {
            return Result.error("密码不能为空");
        }

        boolean success = userService.register(registerDto);
        if (success) {
            return Result.success("注册成功", null);
        } else {
            return Result.error("用户名已存在");
        }
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody LoginDto loginDto) {

        if (loginDto.getUsername() == null || loginDto.getUsername().isEmpty()) {
            return Result.error("用户名不能为空");
        }
        if (loginDto.getPassword() == null || loginDto.getPassword().isEmpty()) {
            return Result.error("密码不能为空");
        }

        String token = userService.login(loginDto);
        if (token != null) {
            return Result.success("登录成功", token);
        } else {
            return Result.error("用户名或密码错误");
        }
    }
}