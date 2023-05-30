package com.example.shop.controller;

import com.example.shop.entity.Cart;
import com.example.shop.entity.Order;
import com.example.shop.entity.Product;
import com.example.shop.entity.User;
import com.example.shop.repository.ProductRepository;
import com.example.shop.security.CurrentUser;
import com.example.shop.service.CartService;
import com.example.shop.service.OrderService;
import com.example.shop.service.ProductService;
import com.example.shop.util.ProductUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cardService;
    private final ProductService productService;
    private final OrderService orderService;

    @GetMapping("/add")
    public String addProductToCart(@AuthenticationPrincipal CurrentUser currentUser, @RequestParam("productId") int productId) {
        User user = currentUser.getUser();
        Optional<Cart> cartOptionalFromDb = cardService.findCartByUserId(user.getId());
        Optional<Product> byId = productService.findById(productId);
        if (cartOptionalFromDb.isPresent()) {
             Cart cart = cartOptionalFromDb.get();
             cart.getProductList().add(byId.get());
             cardService.saveCart(cart);
        }
        return "redirect:/";
    }
    @GetMapping("/see/context")
    public String order(@AuthenticationPrincipal CurrentUser currentUser, ModelMap modelMap) {
        User user = currentUser.getUser();
        Optional<Cart> cartByUserId = cardService.findCartByUserId(user.getId());
        Set<Product> productList = cartByUserId.get().getProductList();
        modelMap.addAttribute("totalSum", ProductUtil.countPrice(productList));
        modelMap.addAttribute("userProducts", productList);
        return "orders";
    }

    @GetMapping("/do/order")
    public String order(@AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
        Optional<Cart> cartByUserId = cardService.findCartByUserId(user.getId());
        if (cartByUserId.isPresent()) {
            Cart cart = cartByUserId.get();
            Order order = Order.builder()
                    .user(user)
                    .dateTime(new Date())
                    .productList(new LinkedHashSet<>(cart.getProductList()))
                    .build();
            orderService.saveOrder(order);
            cardService.saveCart(cart);
        }
        return "redirect:/";

    }
    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable("productId") int id,@AuthenticationPrincipal CurrentUser currentUser){
        User user = currentUser.getUser();
        Optional<Cart> cartByUserId = cardService.findCartByUserId(user.getId());
        if (cartByUserId.isPresent()){
            Cart cart = cartByUserId.get();
            Set<Product> updatedProducts = ProductUtil.deleteById(cart.getProductList(), id);
            cart.setProductList(updatedProducts);
            cardService.saveCart(cart);
        }
        return "redirect:/cart/see/context";

    }

}
