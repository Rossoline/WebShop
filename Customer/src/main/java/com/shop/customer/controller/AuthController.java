package com.shop.customer.controller;

import com.shop.library.dto.CustomerDto;
import com.shop.library.model.Customer;
import com.shop.library.service.CustomerService;
import javax.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {
    private final CustomerService customerService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(CustomerService customerService,
                          BCryptPasswordEncoder passwordEncoder){
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("customerDto", new CustomerDto());
        return "register";
    }

    @PostMapping("/do-register")
    public String register(@Valid @ModelAttribute("customerDto") CustomerDto customerDto,
                           BindingResult result,
                           Model model){
        try{
            if(result.hasErrors()){
                model.addAttribute("customerDto", customerDto);
                return "register";
            }else if(checkRegister(customerDto, model)){
                return "register";
            }
        }catch(Exception e){
            model.addAttribute("error", "Problem on server!");
            model.addAttribute("customerDto", customerDto);
            throw new RuntimeException("Server error in register customer: ", e);
        }
        return "register";
    }

    private boolean checkRegister(CustomerDto customerDto, Model model){
        Customer customer = customerService.findByUsername(customerDto.getUserName());
        boolean noErrors = true;
        if(customer != null){
            model.addAttribute("username", "Username have been already registered!");
            model.addAttribute("customerDto", customerDto);
            noErrors = false;
        }
        else if(!customerDto.getPassword().equals(customerDto.getRepeatPassword())){
            model.addAttribute("password", "Password is not the same!");
            model.addAttribute("customerDto", customerDto);
            noErrors = false;
        }else{
            customerDto.setPassword(passwordEncoder.encode(customerDto.getPassword()));
            customerService.save(customerDto);
            model.addAttribute("success", "Register successfully!");
        }
        return  noErrors;
    }
}
