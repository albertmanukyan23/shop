package com.example.shop.controller;

import com.example.shop.entity.Product;
import com.example.shop.service.CategoryService;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    @GetMapping
    public String products(ModelMap modelMap) {
        modelMap.addAttribute("products", productService.findAllProducts());
        return "products";
    }

    @GetMapping("/remove")
    public String productRemove(@RequestParam("id") int id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }

    @GetMapping("/create")
    public String createProduct(ModelMap modelMap ) {
        modelMap.addAttribute("categories",categoryService.getAll());
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product product,
                               @RequestParam("image") MultipartFile multipartFile) throws IOException {
        productService.addProduct(multipartFile,product);
        return "redirect:/products";
    }

}
