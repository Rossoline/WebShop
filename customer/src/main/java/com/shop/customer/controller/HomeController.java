package com.shop.customer.controller;

import com.shop.customer.library.dto.ProductDto;
import com.shop.customer.library.model.CartItem;
import com.shop.customer.library.model.Category;
import com.shop.customer.library.model.Customer;
import com.shop.customer.library.model.ShoppingCart;
import com.shop.customer.library.model.enums.ActivationStatus;
import com.shop.customer.library.service.CategoryService;
import com.shop.customer.library.service.CustomerService;
import com.shop.customer.library.service.ProductService;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CustomerService customerService;

    public HomeController(ProductService productService, CategoryService categoryService,
                          CustomerService customerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.customerService = customerService;
    }

    @RequestMapping (value = {"/home", "/"}, method = RequestMethod.GET)
    public String home(Principal principal, Model model, HttpSession session) {
        if (principal != null) {
            session.setAttribute("username", principal.getName());
            Customer customer = customerService.findByUsername(principal.getName());
            ShoppingCart cart = customer.getShoppingCart();
            session.setAttribute("totalItems",
                    cart.getCartItems().stream().map(CartItem::getQuantity).count());
        } else {
            session.removeAttribute("username");
        }
        return "home";
    }

    @GetMapping ("/main-in-store")
    public String index(Model model) {
        List<Category> categories = categoryService.findAll();
        List<ProductDto> productDtos = productService.findAll().stream()
                .filter(p -> p.getStatus() == ActivationStatus.ACTIVATED)
                .collect(Collectors.toList());
        model.addAttribute("categories", categories);
        model.addAttribute("products", productDtos);
        return "main-in-store";
    }

    @GetMapping("/contact-us")
    public String contact() {
        return "contact-us";
    }

}
