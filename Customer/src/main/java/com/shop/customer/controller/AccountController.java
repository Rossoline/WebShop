package com.shop.customer.controller;

import com.shop.library.model.Customer;
import com.shop.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;

@Controller
public class AccountController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/account")
    public String accountHome(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        String userName = principal.getName();
        Customer customer = customerService.findByUsername(userName);
        model.addAttribute("customer", customer);
        return "account";
    }

    @RequestMapping(value = "update-info", method = {RequestMethod.GET, RequestMethod.PUT})
    public String updateCustomer(
            @ModelAttribute("customer") Customer customer,
            Model model,
            RedirectAttributes attributes,
            Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        Customer customerSaved = customerService.saveInfo(customer);
        attributes.addFlashAttribute("customer", customerSaved);
        return "redirect:/account";
    }
}
