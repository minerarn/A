package com.example.a.controller.admin;

import com.example.a.entity.Post;
import com.example.a.entity.Type;
import com.example.a.service.PostService;
import com.example.a.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller("adminPostController") // 1. 必须明确 Bean 名称，防止与前台 PostController 冲突
@RequestMapping("/admin/posts")   // 2. 统一前缀，对应 SecurityConfig 中的跳转路径
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private TypeService typeService;

    /**
     * 后台列表页：对应 http://localhost:8080/admin/posts
     */
    @GetMapping
    public String list(@RequestParam(required = false) String title,
                       @RequestParam(required = false) String author,
                       @RequestParam(required = false) Integer minViews,
                       @RequestParam(required = false) Long typeId,
                       Model model) {
        // 使用动态查询获取结果
        List<Post> posts = postService.search(title, author, minViews, typeId);
        model.addAttribute("posts", posts);
        model.addAttribute("title", title);
        model.addAttribute("author", author);
        model.addAttribute("minViews", minViews);
        model.addAttribute("typeId", typeId);
        model.addAttribute("types", typeService.findAll());
        
        // 如果通过类别筛选，获取并传递类别名称
        if (typeId != null) {
            Type selectedType = typeService.findById(typeId);
            if (selectedType != null) {
                model.addAttribute("selectedTypeName", selectedType.getName());
            }
        }

        // 3. 对应物理路径：src/main/resources/templates/admin/postList.html
        return "admin/postList";
    }

    @GetMapping("/new")
    public String newPost(Model model) {
        model.addAttribute("post", new Post());
        // 确保 Service 方法名与你的逻辑一致
        model.addAttribute("types", typeService.findAll());
        return "admin/postForm";
    }

    @PostMapping("/save")
    public String create(@ModelAttribute Post post,
                         @RequestParam(required = false) String newTypeName,
                         Model model) {
        if (newTypeName != null) {
            newTypeName = newTypeName.trim();
        }

        if (post.getTypeId() == null && (newTypeName == null || newTypeName.isEmpty())) {
            model.addAttribute("post", post);
            model.addAttribute("types", typeService.findAll());
            model.addAttribute("error", "请选择一个分类，或在下方输入要新增的类别名称");
            return "admin/postForm";
        }

        if (post.getTypeId() == null && newTypeName != null && !newTypeName.isEmpty()) {
            Type type = new Type();
            type.setName(newTypeName);
            typeService.save(type);
            post.setTypeId(type.getId());
        }

        post.setCreateTime(LocalDateTime.now());
        post.setViews(0);
        postService.insert(post); // 确保你的 PostService 中是 insert 而不是 save
        return "redirect:/admin/posts";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        model.addAttribute("types", typeService.findAll());
        return "admin/postForm";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Post post) {
        // 注意：更新时通常需要先查出原对象以保持创建时间等字段不被覆盖
        postService.update(post);
        return "redirect:/admin/posts";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/admin/posts";
    }
}