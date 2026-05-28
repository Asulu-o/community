package org.example.community.service;

import org.example.community.dto.LoginDto;
import org.example.community.dto.RegisterDto;
import org.example.community.entity.User;

public interface UserService {
    boolean register(RegisterDto registerDto);
    User findByUsername(String username);
    String login(LoginDto loginDto);

    // 新增：根据用户名获取用户ID
    Long getUserIdByUsername(String username);
}