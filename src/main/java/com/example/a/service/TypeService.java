package com.example.a.service;

import com.example.a.entity.Type;

import java.util.List;

public interface TypeService {
    List<Type> findAll();
    Type findById(Long id);
    void save(Type type);
    void update(Type type);
    void delete(Long id);
    List<Type> searchByName(String name);
    List<Type> findAllWithPostCount();
}