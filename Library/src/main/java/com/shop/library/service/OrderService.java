package com.shop.library.service;

import com.shop.library.model.Order;
import com.shop.library.model.ShoppingCart;
import java.util.List;

public interface OrderService {
    void save(ShoppingCart cart);

    List<Order> findAll();

    Double totalPrice(Order order);

    void acceptOrder(Long id);

    void cancelOrder(Long id);
}
