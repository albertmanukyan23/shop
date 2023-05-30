package com.example.shop.service;

import com.example.shop.entity.Cart;

import java.util.Optional;

public interface CartService {

    Optional<Cart> findCartByUserId(int id);


    void saveCart(Cart cart);

}
