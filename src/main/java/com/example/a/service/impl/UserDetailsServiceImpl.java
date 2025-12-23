package com.example.a.service.impl;

import com.example.a.entity.User;
import com.example.a.mapper.UserMapper; // 1. 修正拼写
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper; // 1. 修正拼写

    // UserDetailsServiceImpl.java
    // UserDetailsServiceImpl.java
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 关键：不要做任何 replace 处理，直接传入数据库里的 "ROLE_ADMIN"
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole()) // 这里会得到 ROLE_ADMIN
                .build();
    }
}