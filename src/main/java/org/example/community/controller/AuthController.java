package org.example.community.controller;

import org.example.community.common.Result;
import org.example.community.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/info")
    public Result<?> getUserInfo(@RequestHeader("Authorization") String authorization) {

        String token = authorization.replace("Bearer ", "");

        if (jwtUtil.validateToken(token)) {
            String username = jwtUtil.getUsernameFromToken(token);
            Map<String, String> data = new HashMap<>();
            data.put("username", username);
            return Result.success("Token 验证成功", data);
        } else {
            return Result.error("Token 无效或已过期");
        }
    }
}