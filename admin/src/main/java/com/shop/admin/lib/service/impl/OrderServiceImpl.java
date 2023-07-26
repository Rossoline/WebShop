package com.shop.admin.lib.service.impl;

import com.shop.admin.lib.model.CartItem;
import com.shop.admin.lib.model.Order;
import com.shop.admin.lib.model.ShoppingCart;
import com.shop.admin.lib.model.enums.OrderStatus;
import com.shop.admin.lib.repository.OrderRepository;
import com.shop.admin.lib.service.CustomerService;
import com.shop.admin.lib.service.OrderService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final CustomerService customerService;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(CustomerService customerService,
                            OrderRepository orderRepository) {
        this.customerService = customerService;
        this.orderRepository = orderRepository;
    }

    @Override
    public void save(ShoppingCart cart) {
        Order order = new Order();
        Set<CartItem> cartItemSet = new HashSet<>(cart.getCartItems());
        order.setOrderDate(new Date());
        order.setOrderStatus(OrderStatus.APPROVAL);
        order.setCustomer(customerService.findCustomerByCart(cart));
        order.setCartItems(cartItemSet);
        orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public BigDecimal totalPrice(Order order) {
        return order.getCartItems().stream().map(c -> c.getProduct().getCostPrice()
                        .multiply(BigDecimal.valueOf(c.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void acceptOrder(Long id) {
        Order order = orderRepository.getById(id);
        order.setOrderStatus(OrderStatus.DELIVERY);
        orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
