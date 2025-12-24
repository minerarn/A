package com.example.a.controller;

import com.example.a.entity.Post;
import com.example.a.entity.Type;
import com.example.a.service.PostService;
import com.example.a.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller("frontPostController")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private TypeService typeService;

    // 首页：展示所有博文（仅显示启用类别下的博文）
    @GetMapping("/")
    public String home(Model model) {
        // 仅查询启用类别下的博文
        List<Post> posts = postService.findAllWithEnabledType();
        List<Type> types = typeService.findAllEnabled(); // 仅获取启用的类别
        model.addAttribute("posts", posts);
        model.addAttribute("types", types);
        return "front/postList"; // 直接跳到前台列表页
    }

    // 按类别查看博文（仅显示启用类别下的博文）
    @GetMapping("/types/{typeId}/posts")
    public String postsByType(@PathVariable Long typeId, Model model) {
        // 作业要求：点击类别名称查看该类别下所有博文
        List<Post> posts = postService.findByEnabledTypeId(typeId);
        List<Type> types = typeService.findAllEnabled();
        model.addAttribute("posts", posts);
        model.addAttribute("types", types);
        return "front/postList";
    }

    // 查看博文详情并增加阅读量
    @GetMapping("/posts/{id}")
    public String postDetail(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        if (post != null) {
            // 实现阅读量递增逻辑
            post.setViews(post.getViews() + 1);
            postService.update(post);
        }
        model.addAttribute("post", post);
        return "front/postdetail"; // 建议统一放在 front 目录下
    }
}