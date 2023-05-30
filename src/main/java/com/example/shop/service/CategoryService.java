package com.example.shop.service;

import com.example.shop.entity.Category;

import java.util.List;

public interface CategoryService {


    List<Category> getAll();

    void deleteCategory(int id);

    void addCategory(Category category);
}
