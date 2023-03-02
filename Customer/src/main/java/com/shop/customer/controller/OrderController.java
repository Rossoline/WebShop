package com.shop.customer.controller;

import com.shop.library.model.Customer;
import com.shop.library.model.Order;
import com.shop.library.model.ShoppingCart;
import com.shop.library.service.CustomerService;
import com.shop.library.service.OrderService;
import java.security.Principal;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    private final CustomerService customerService;
    private final OrderService orderService;

    public OrderController(CustomerService customerService,
                           OrderService orderService){
        this.customerService = customerService;
        this.orderService = orderService;
    }

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
            model.addAttribute("customer", customer);
            ShoppingCart cart = customer.getShoppingCart();
            model.addAttribute("cart", cart);
        }
        return "checkout";
    }

    @GetMapping("/order")
    public String order(Principal principal, Model model){
        if(principal == null){
            return "redirect:/login";
        }
        String userName = principal.getName();
        Customer customer = customerService.findByUsername(userName);
        List<Order> orderList = customer.getOrders();
        model.addAttribute("orders", orderList);
        return "order";
    }

    @GetMapping("/save-order")
    public String saveOrder(Principal principal, Model model){
        if(principal == null){
            return "redirect:/login";
        }
        String userName = principal.getName();
        Customer customer = customerService.findByUsername(userName);
        ShoppingCart cart = customer.getShoppingCart();
        orderService.save(cart);
        return "redirect:/order";
    }
}
