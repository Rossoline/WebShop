package com.shop.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.security.Principal;

@Controller
public class CartController {

    public String cart(Model model, Principal principal){
        if (principal == null){
            return "redirect:/login";
        }

        return "cart";
    }
}
