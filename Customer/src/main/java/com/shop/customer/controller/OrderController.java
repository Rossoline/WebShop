package com.shop.customer.controller;

import com.shop.library.model.Customer;
import com.shop.library.model.ShoppingCart;
import com.shop.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class OrderController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/checkout")
    public String checkout(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        String userName = principal.getName();
        Customer customer = customerService.findByUsername(userName);
        if(customer.getPhoneNumber().trim().isEmpty()){
            model.addAttribute("customer", customer);
            model.addAttribute("error", "You must fill your phone number!");
            return "account";
        }else{
            model.addAttribute("customer",customer);
            ShoppingCart cart = customer.getShoppingCart();
            model.addAttribute("cart", cart);
        }
        return "checkout";
    }

    @GetMapping("/order")
    public String order(){
        return "order";
    }
}
