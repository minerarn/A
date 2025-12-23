package com.example.a.entity;

public class User {
    private Long id; // 建议统一使用 Long，与 Post/Type 保持一致
    private String username;
    private String password;
    private String role; // 存储如 "ROLE_ADMIN" 或 "ROLE_USER"

    // Getter 和 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}