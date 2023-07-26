package com.shop.customer.library.service;

import com.shop.customer.library.model.Order;
import com.shop.customer.library.model.ShoppingCart;
import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    void save(ShoppingCart cart);

    List<Order> findAll();

    BigDecimal totalPrice(Order order);

    void acceptOrder(Long id);

    void cancelOrder(Long id);
}
