package com.example.a.mapper;

import com.example.a.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TypeMapper {
    List<Type> findAll();
    Type findById(Long id);
    void insert(Type type);
    void update(Type type);
    void delete(Long id);
    List<Type> searchByName(@Param("name") String name);
    List<Type> findAllWithPostCount();
}