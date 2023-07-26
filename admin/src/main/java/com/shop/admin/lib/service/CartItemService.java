package com.shop.admin.lib.service;

import com.shop.admin.lib.model.CartItem;

public interface CartItemService {
    CartItem save(CartItem cartItem);

    void delete(CartItem cartItem);
}
