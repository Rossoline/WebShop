package com.shop.library.service;

import com.shop.library.model.CartItem;

public interface CartItemService {
    CartItem save(CartItem cartItem);

    void delete(CartItem cartItem);
    void deleteNotUsed();
}
