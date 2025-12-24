package com.example.a.mapper;

import com.example.a.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    // 1. 获取所有博文（用于后台列表或首页）
    List<Post> findAll();

    // 2. 根据 ID 查询详情
    Post findById(Long id);

    // 3. 核心功能：多条件动态搜索（标题、作者、最低点击量）
    List<Post> search(@Param("title") String title,
                      @Param("author") String author,
                      @Param("minViews") Integer minViews,
                      @Param("typeId") Long typeId);

    // 4. 根据分类筛选博文
    List<Post> findByTypeId(Long typeId);

    // 5. 增删改操作
    void insert(Post post);
    void update(Post post);
    void delete(Long id);
}