package com.example.shop.util;

import com.example.shop.entity.Product;

import java.util.Set;

public class ProductUtil {
    public static double countPrice(Set<Product> products){
        int sum = 0;
        for (Product product : products) {
            sum+= product.getPrice();
        }
        return sum;
    }

    public static  Set<Product> deleteById(Set<Product> productList, int id) {
        productList.removeIf(product -> product.getId() == id);
        return productList;
    }
}
