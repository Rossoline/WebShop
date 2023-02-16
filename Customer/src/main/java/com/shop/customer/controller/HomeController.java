package com.shop.customer.controller;

import com.shop.library.dto.ProductDto;
import com.shop.library.model.Category;
import com.shop.library.service.CategoryService;
import com.shop.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(value = {"/index", "/"}, method = RequestMethod.GET)
    public String home(Model model) {
        return "home";
    }
    @GetMapping("/home")
    public String index(Model model){
        List<Category> categories = categoryService.findAll();
        List<ProductDto> productDtos = productService.findAll().stream()
                .filter(ProductDto::isActivated)
                .collect(Collectors.toList());
        model.addAttribute("categories", categories);
        model.addAttribute("products", productDtos);
        return "index";
    }
}
