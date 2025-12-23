package com.example.a.controller.admin;

import com.example.a.entity.Type;
import com.example.a.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/types")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("types", typeService.findAll());
        return "admin/typeList";
    }

    @GetMapping("/new")
    public String newType(Model model) {
        model.addAttribute("type", new Type());
        return "admin/typeForm";
    }

    @PostMapping
    public String create(@ModelAttribute Type type) {
        // 默认启用状态
        if (type.getEnabled() == null) {
            type.setEnabled(1); // 1表示启用，0表示禁用
        }
        typeService.save(type);
        return "redirect:/admin/types";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Type type = typeService.findById(id.longValue());
        model.addAttribute("type", type);
        return "admin/typeForm";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Type type) {
        typeService.update(type);
        return "redirect:/admin/types";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        typeService.delete(id.longValue());
        return "redirect:/admin/types";
    }
}