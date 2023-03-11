package com.shop.customer.controller;

import com.shop.library.model.Customer;
import com.shop.library.service.CustomerService;
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {
    private final CustomerService customerService;

    public AccountController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping ("/account")
    public String accountHome(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        String userName = principal.getName();
        Customer customer = customerService.findByUsername(userName);
        model.addAttribute("customer", customer);
        return "account";
    }

    @RequestMapping (value = "update-info", method = {RequestMethod.GET, RequestMethod.PUT})
    public String updateCustomer(@ModelAttribute ("customer") Customer customer, Model model,
                                 RedirectAttributes attributes, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        Customer customerSaved = customerService.saveInfo(customer);
        attributes.addFlashAttribute("customer", customerSaved);
        return "redirect:/account";
    }
}
