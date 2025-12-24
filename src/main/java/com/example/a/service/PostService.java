package com.example.a.service;

import com.example.a.entity.Post;
import java.util.List;

public interface PostService {

    // 1. 获取所有博文（用于后台列表展示）
    List<Post> findAll();

    // 2. 根据分类筛选（保留此方法供前台或特定场景使用）
    List<Post> list(Integer typeId);

    // 3. 根据 ID 查询详情
    Post findById(Long id);

    // 4. 保存新博文
    void save(Post post);

    // 5. 更新博文内容（包括阅读量累加）
    void update(Post post);

    // 6. 重要：改为 Long id，确保与 Mapper 和 LogAspect 匹配
    void delete(Long id);

    // 7. 多条件搜索功能
    List<Post> search(String title, String author, Integer minViews, Long typeId);

    // 8. 前台分类筛选功能
    List<Post> findByTypeId(Long typeId);

    void insert(Post post);
    
    // 9. 查询启用类别下的所有博文（用于前台显示）
    List<Post> findAllWithEnabledType();
    
    // 10. 查询指定启用类别下的博文（用于前台分类筛选）
    List<Post> findByEnabledTypeId(Long typeId);
}