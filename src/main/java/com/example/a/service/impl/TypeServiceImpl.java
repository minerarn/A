package com.example.a.service.impl;

import com.example.a.entity.Type;
import com.example.a.mapper.PostMapper;
import com.example.a.mapper.TypeMapper;
import com.example.a.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    private final TypeMapper typeMapper;
    
    @Autowired
    private PostMapper postMapper;

    public TypeServiceImpl(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    @Override
    public List<Type> findAll() {
        // 建议后台管理列表直接使用带统计的方法，增加数据丰富度
        return typeMapper.findAllWithPostCount();
    }

    @Override
    public Type findById(Long id) {
        return typeMapper.findById(id);
    }

    @Override
    public void save(Type type) {
        // 设置默认值：如果创建时未指定状态，默认启用 (1)
        if (type.getEnabled() == null) {
            type.setEnabled(1);
        }
        typeMapper.insert(type);
    }

    @Override
    public void update(Type type) {
        typeMapper.update(type);
    }

    @Override
    public void delete(Long id) {
        // 此处删除操作会被 LogAspect 拦截记录日志
        if (id != null) {
            // 先删除该类别下的所有博文
            postMapper.deleteByTypeId(id);
            // 再删除类别
            typeMapper.delete(id);
        }
    }

    @Override
    public List<Type> searchByName(String name) {
        return typeMapper.searchByName(name);
    }

    @Override
    public List<Type> findAllWithPostCount() {
        return typeMapper.findAllWithPostCount();
    }

    @Override
    public List<Type> findAllEnabled() {
        return typeMapper.findAllEnabled();
    }
}