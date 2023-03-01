package com.shop.library.service;

import com.shop.library.model.ShoppingCart;

public interface OrderService {
    void save(ShoppingCart cart);

    void acceptOrder(Long id);

    void cancelOrder(Long id);
}
