package com.shop.admin.controller;

import com.shop.admin.lib.dto.AdminDto;
import com.shop.admin.lib.model.Admin;
import com.shop.admin.lib.service.AdminService;
import java.security.Principal;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    private final AdminService adminService;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginController(AdminService adminService, BCryptPasswordEncoder passwordEncoder) {
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping ("/login")
    public String loginForm(Model model) {
        model.addAttribute("title", "Login");
        return "login";
    }

    @RequestMapping (value = {"/index", "/"})
    public String home(Model model, Principal principal, HttpSession session) {
        model.addAttribute("title", "Home page");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        } else if (principal != null) {
            session.setAttribute("username", principal.getName());
        } else {
            session.removeAttribute("username");
        }
        return "index";
    }

    @GetMapping ("/register")
    public String register(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("adminDto", new AdminDto());
        return "register";
    }

    @GetMapping ("/forgot-password")
    public String forgotPassword(Model model) {
        model.addAttribute("title", "Forgot password");
        return "forgot-password";
    }

    @PostMapping ("/register-new")
    public String addAdmin(@Valid @ModelAttribute ("adminDto") AdminDto adminDto,
                           BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("adminDto", adminDto);
                return "register";
            } else if (checkRegister(model, adminDto)) {
                return "register";
            }
        } catch (Exception e) {
            model.addAttribute("errors", "Server problem!");
            throw new RuntimeException("Server error in register new admin: ", e);
        }
        return "register";
    }

    private boolean checkRegister(Model model, AdminDto adminDto) {
        Admin admin = adminService.findByUsername(adminDto.getUsername());
        boolean noErrors = true;
        if (admin != null) {
            model.addAttribute("adminDto", adminDto);
            model.addAttribute("emailError", "This email already registered!");
            noErrors = false;
        } else if (!adminDto.getPassword().equals(adminDto.getRepeatPassword())) {
            model.addAttribute("adminDto", adminDto);
            model.addAttribute("passwordError", "Wrong repeat password!");
            noErrors = false;
        } else {
            adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
            adminService.save(adminDto);
            model.addAttribute("success", "Register successfully!");
            model.addAttribute("adminDto", adminDto);
        }
        return noErrors;
    }
}
