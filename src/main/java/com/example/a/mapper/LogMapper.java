package com.example.a.mapper;

import com.example.a.entity.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {
    void insert(Log log);
    List<Log> findAll();
}

