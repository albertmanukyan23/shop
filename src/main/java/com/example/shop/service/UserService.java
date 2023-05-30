package com.example.shop.service;

import com.example.shop.entity.User;

import java.util.List;

public interface UserService {
    void registerUser(User user);

    List<User> getAllUsers();

    void deleteUser(int id);

}
