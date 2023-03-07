package com.shop.customer.controller;

import com.shop.library.model.Customer;
import com.shop.library.model.Product;
import com.shop.library.model.ShoppingCart;
import com.shop.library.service.CustomerService;
import com.shop.library.service.ProductService;
import com.shop.library.service.ShoppingCartService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {
    private final CustomerService customerService;
    private final ShoppingCartService cartService;
    private final ProductService productService;

    public CartController(CustomerService customerService,
                          ShoppingCartService cartService,
                          ProductService productService){
        this.customerService = customerService;
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping("/cart")
    public String cart(Model model, Principal principal, HttpSession session) {
        if (principal == null) {
            return "redirect:/login";
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        ShoppingCart shoppingCart = customer.getShoppingCart();
        if (shoppingCart == null) {
            model.addAttribute("check", "No item in your cart");
        }
        model.addAttribute("subTotal",cartService.totalPrice(shoppingCart));
        model.addAttribute("shoppingCart", shoppingCart);
        return "cart";
    }

    @PostMapping("/add-to-cart")
    public String addItemCart(
            @RequestParam("id") Long productId,
            @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
            Principal principal,
            HttpServletRequest request) {
        if (principal == null) {
            return "redirect:/login";
        }
        Product product = productService.getProductById(productId);
        String userName = principal.getName();
        Customer customer = customerService.findByUsername(userName);
        cartService.addItemToCart(product, quantity, customer);
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/update-cart",
            method = RequestMethod.POST, params = "action=update")
    public String updateCart(@RequestParam("quantity") int quantity,
                             @RequestParam("id") Long productId,
                             Model model,
                             Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            String userName = principal.getName();
            Customer customer = customerService.findByUsername(userName);
            Product product = productService.getProductById(productId);
            ShoppingCart cart = cartService.updateItemCart(product, quantity, customer);
            model.addAttribute("shoppingCart", cart);
            return "redirect:/cart";
        }
    }

    @RequestMapping(value = "/update-cart",
            method = RequestMethod.POST, params = "action=delete")
    public String deleteItemFromCart(@RequestParam("id") Long productId,
                                     Model model,
                                     Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            String userName = principal.getName();
            Customer customer = customerService.findByUsername(userName);
            Product product = productService.getProductById(productId);
            ShoppingCart cart = cartService.deleteItemFromCart(product, customer);
            model.addAttribute("shoppingCart", cart);
            return "redirect:/cart";
        }
    }
}
