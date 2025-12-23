package com.example.a.aspect;

import com.example.a.entity.Log;
import com.example.a.mapper.LogMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogMapper logMapper;

    @After("execution(* com.example.a.service.PostService.delete(..))")
    public void recordPostDelete(JoinPoint joinPoint) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Object[] args = joinPoint.getArgs();
        String postId = args.length > 0 ? args[0].toString() : "unknown";

        Log log = new Log();
        log.setUsername(username);
        log.setOperation("删除博文 - ID: " + postId);
        log.setOperateTime(LocalDateTime.now());

        logMapper.insert(log);
    }

    @After("execution(* com.example.a.service.TypeService.delete(..))")
    public void recordTypeDelete(JoinPoint joinPoint) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Object[] args = joinPoint.getArgs();
        String typeId = args.length > 0 ? args[0].toString() : "unknown";

        Log log = new Log();
        log.setUsername(username);
        log.setOperation("删除类别 - ID: " + typeId);
        log.setOperateTime(LocalDateTime.now());

        logMapper.insert(log);
    }
}