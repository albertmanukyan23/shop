package com.example.shop.controller;

import com.example.shop.entity.Cart;
import com.example.shop.entity.User;
import com.example.shop.security.CurrentUser;
import com.example.shop.service.CartService;
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
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final CartService cartService;


    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/all")
    public String getAllUsers(ModelMap modelMap) {
        List<User> allUsers = userService.getAllUsers();
        modelMap.addAttribute("users",allUsers);
        return "users";

    }
    @GetMapping("/remove")
    public String removeUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/user/all";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        Cart cart = Cart.builder()
                .user(user)
                .productList(new LinkedHashSet<>())
                .build();
        userService.registerUser(user);
        cartService.saveCart(cart);
        return "redirect:/";
    }
    @GetMapping("/admin")
    public  String admin(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap){
        modelMap.addAttribute("user",currentUser);
        return "admin";
    }
}
