package org.example.community;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 临时关闭所有安全拦截，所有接口都可以直接访问
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()   // 所有请求都允许，不需要登录
                )
                .csrf(csrf -> csrf.disable());  // 关闭CSRF保护

        return http.build();
    }
}