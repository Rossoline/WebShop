package com.shop.customer.library.service;

import com.shop.customer.library.model.Customer;
import com.shop.customer.library.model.Product;
import com.shop.customer.library.model.ShoppingCart;
import java.math.BigDecimal;
import java.util.List;

public interface ShoppingCartService {
    ShoppingCart save(ShoppingCart shoppingCart);

    void saveEmpty(ShoppingCart shoppingCart);

    void addItemToCart(Product product, int quantity, Customer customer);

    ShoppingCart updateItemCart(Product product, int quantity, Customer customer);

    ShoppingCart deleteItemFromCart(Product product, Customer customer);

    BigDecimal totalPrice(ShoppingCart shoppingCart);

    List<ShoppingCart> findAll();
}
