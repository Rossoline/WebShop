package com.shop.customer.controller;

import com.shop.library.model.Product;
import com.shop.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller

public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String products(Model model){
        List<Product> products = productService.getAllProducts();
        List<Product> listViewProducts = productService.listViewProducts();
        model.addAttribute("viewProducts", listViewProducts);
        model.addAttribute("products", products);
        return "shop";
    }

    @GetMapping("/find-product/{id}")
    public String findProductById(@PathVariable("id") Long id, Model model){
        Product product = productService.getProductById(id);
        Long categoryId = product.getCategory().getId();
        List<Product> products = productService.getRelatedProducts(categoryId);
        model.addAttribute("product", product);
        model.addAttribute("products", products);
        return "product-detail";
    }

}
