package com.shop.library.service.impl;

import com.shop.library.model.CartItem;
import com.shop.library.model.Order;
import com.shop.library.model.ShoppingCart;
import com.shop.library.model.enums.OrderStatus;
import com.shop.library.repository.OrderRepository;
import com.shop.library.service.CustomerService;
import com.shop.library.service.OrderService;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final CustomerService customerService;
    private final OrderRepository orderRepository;

    public OrderServiceImpl(CustomerService customerService, OrderRepository orderRepository){
        this.customerService = customerService;
        this.orderRepository = orderRepository;
    }

    @Override
    public void save(ShoppingCart cart){
        Order order = new Order();
        Set<CartItem> cartItemSet = new HashSet<>(cart.getCartItems());
        order.setOrderDate(new Date());
        order.setOrderStatus(OrderStatus.APPROVAL);
        order.setNotes("");
        order.setCustomer(customerService.findCustomerByCart(cart));
        order.setCartItems(cartItemSet);
        orderRepository.save(order);
    }

    @Override public Double totalPrice(Order order){
        return order.getCartItems().stream()
                .map(c -> c.getQuantity() * c.getProduct().getCostPrice())
                .mapToDouble(Double :: doubleValue).sum();
    }

    @Override
    public void acceptOrder(Long id){
        Order order = orderRepository.getById(id);
        order.setOrderStatus(OrderStatus.DELIVERY);
        orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Long id){
        orderRepository.deleteById(id);
    }
}
