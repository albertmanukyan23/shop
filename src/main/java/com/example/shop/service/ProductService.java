package com.example.shop.service;

import com.example.shop.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAll();

    Optional<Product> findById(int productId);

    List<Product> findAllProducts();

    void deleteProductById(int id);

    void addProduct(MultipartFile multipartFile, Product product) throws IOException;

}
