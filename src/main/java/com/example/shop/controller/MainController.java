package com.example.shop.controller;

import com.example.shop.entity.Product;
import com.example.shop.entity.UserType;
import com.example.shop.security.CurrentUser;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    @Value("${hospital.upload.image.path}")
    private String imageUploadPath;

    private final ProductService productService;

    @GetMapping("/")
    public String mainProducts(ModelMap modelMap) {
        List<Product> all = productService.getAll();
        modelMap.addAttribute("products", all);
        return "index";
    }
    @GetMapping("customLogin")
    public String customLogin(){
        return "customLogin";

    }
    @GetMapping("/customSuccessLogIn")
    public String customSuccessLogIn(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser != null){
            if (currentUser.getUser().getType() == UserType.ADMIN){
                return "redirect:/user/admin";
            }
            else{
                return "redirect:/";
            }
        }
        return "customLogin";

    }

    @GetMapping(value = "/getImage",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("imageName") String imageName) throws IOException {
        File file = new File(imageUploadPath + imageName);
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(file);
            return IOUtils.toByteArray(fis);
        }
        return null;
    }
}
