package com.shop.admin.lib.service;

import com.shop.admin.lib.model.Order;
import com.shop.admin.lib.model.ShoppingCart;
import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    void save(ShoppingCart cart);

    List<Order> findAll();

    BigDecimal totalPrice(Order order);

    void acceptOrder(Long id);

    void cancelOrder(Long id);
}
