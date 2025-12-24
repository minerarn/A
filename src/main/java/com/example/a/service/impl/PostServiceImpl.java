package com.example.a.service.impl;

import com.example.a.entity.Post;
import com.example.a.mapper.PostMapper;
import com.example.a.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    // 1. 获取所有博文（对应后台管理列表）
    @Override
    public List<Post> findAll() {
        return postMapper.findAll();
    }

    // 2. 这里的 list 如果不再使用可以删除，或者改为调用 search
    @Override
    public List<Post> list(Integer typeId) {
        if (typeId != null) {
            return postMapper.findByTypeId(typeId.longValue());
        }
        return postMapper.findAll();
    }

    @Override
    public Post findById(Long id) {
        return postMapper.findById(id);
    }

    @Override
    public void save(Post post) {
        postMapper.insert(post);
    }

    @Override
    public void update(Post post) {
        postMapper.update(post);
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            postMapper.delete(id);
        }
    }


    // 4. 多条件搜索实现
    @Override
    public List<Post> search(String title, String author, Integer minViews, Long typeId) {
        return postMapper.search(title, author, minViews, typeId);
    }

    @Override
    public List<Post> findByTypeId(Long typeId) {
        return postMapper.findByTypeId(typeId);
    }

    @Override
    public void insert(Post post) {
        postMapper.insert(post);
    }
}