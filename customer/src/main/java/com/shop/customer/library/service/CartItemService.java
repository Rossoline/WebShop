package com.shop.customer.library.service;

import com.shop.customer.library.model.CartItem;

public interface CartItemService {
    CartItem save(CartItem cartItem);

    void delete(CartItem cartItem);
}
