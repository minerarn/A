package com.example.a.entity;

public class Type {
    private Long id;          // 建议统一使用 Long
    private String name;
    private Integer enabled;  // 1: 启用, 0: 禁用
    private Integer postCount; // 用于统计该类别下的博文数量

    // Getter 和 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    // 辅助方法：方便在页面通过 th:text="${type.statusText}" 显示文字
    public String getStatusText() {
        return this.enabled != null && this.enabled == 1 ? "已启用" : "已禁用";
    }
}