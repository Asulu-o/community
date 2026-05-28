package org.example.community.service.impl;

import org.example.community.dto.LoginDto;
import org.example.community.dto.RegisterDto;
import org.example.community.entity.User;
import org.example.community.mapper.UserMapper;
import org.example.community.service.UserService;
import org.example.community.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public boolean register(RegisterDto registerDto) {
        User existUser = findByUsername(registerDto.getUsername());
        if (existUser != null) {
            return false;
        }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());
        user.setStatus(1);

        int result = userMapper.insert(user);
        return result > 0;
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.selectOne(
                new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<User>()
                        .eq("username", username)
        );
    }

    @Override
    public String login(LoginDto loginDto) {
        User user = findByUsername(loginDto.getUsername());

        if (user == null) {
            return null;
        }

        boolean matches = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());

        if (!matches) {
            return null;
        }

        return jwtUtil.generateToken(user.getUsername());
    }

    // 新增：根据用户名获取用户ID
    @Override
    public Long getUserIdByUsername(String username) {
        User user = findByUsername(username);
        return user != null ? user.getId() : null;
    }
}