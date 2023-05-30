package com.example.shop.controller;

import com.example.shop.entity.Cart;
import com.example.shop.entity.Category;
import com.example.shop.entity.User;
import com.example.shop.security.CurrentUser;
import com.example.shop.service.CartService;
import com.example.shop.service.CategoryService;
import com.example.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashSet;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public String seeCategories(ModelMap modelMap) {
        List<Category> all = categoryService.getAll();
        modelMap.addAttribute("categories", all);
        return "categories";
    }

    @GetMapping("/category/remove")
    public String removeCategory(@RequestParam("id") int id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

    @GetMapping("/category/add")
    public String addCategory() {
        return "addCategory";
    }

    @PostMapping("/category/add")
    public String addCategoryToDb(@ModelAttribute Category category) {
        categoryService.addCategory(category);
        return "redirect:/categories";
    }
}
