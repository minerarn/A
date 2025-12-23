package com.example.a.mapper;

import com.example.a.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper { // 确保这里拼写正确

    // 根据用户名查询用户，用于 Spring Security 登录认证
    User findByUsername(String username);
}