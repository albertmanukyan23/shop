package com.example.shop.service.impl;

import com.example.shop.entity.Order;
import com.example.shop.repository.OrderRepository;
import com.example.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);

    }
}

