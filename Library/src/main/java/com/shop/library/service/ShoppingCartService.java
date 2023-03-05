package com.shop.library.service;

import com.shop.library.model.Customer;
import com.shop.library.model.Product;
import com.shop.library.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart save(ShoppingCart shoppingCart);

    void saveEmpty(ShoppingCart shoppingCart);

    void addItemToCart(Product product, int quantity, Customer customer);

    ShoppingCart updateItemCart(Product product, int quantity, Customer customer);

    ShoppingCart deleteItemFromCart(Product product, Customer customer);

    Double totalPrice(ShoppingCart shoppingCart);
}
