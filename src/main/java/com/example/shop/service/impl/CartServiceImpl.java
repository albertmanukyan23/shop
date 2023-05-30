package com.example.shop.service.impl;

import com.example.shop.entity.Cart;
import com.example.shop.entity.User;
import com.example.shop.repository.CartRepository;
import com.example.shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;


    @Override
    public Optional<Cart> findCartByUserId(int id) {
       return cartRepository.findCartByUserId(id);
    }

    @Override
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }
}

