package com.shop.library.service.impl;

import com.shop.library.model.Customer;
import com.shop.library.model.Order;
import com.shop.library.model.OrderStatus;
import com.shop.library.model.ShoppingCart;
import com.shop.library.repository.CustomerRepository;
import com.shop.library.repository.OrderRepository;
import com.shop.library.repository.ShoppingCartRepository;
import com.shop.library.service.OrderService;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ShoppingCartRepository cartRepository;
    private final CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ShoppingCartRepository cartRepository,
                            CustomerRepository customerRepository){
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void save(ShoppingCart cart){
        Order order = new Order();
        Customer customer = customerRepository.findCustomerByShoppingCart(cart);
        List<Order> customerOrders = customer.getOrders();
        customerOrders.add(order);
        customer.setOrders(customerOrders);
        customerRepository.save(customer);
        order.setOrderDate(new Date());
        order.setOrderStatus(OrderStatus.APPROVAL);
        //TODO add notes for customer
        order.setNotes("");
        order.setCartItems(cart.getCartItems());
        cart.setCartItems(Set.of());
        cartRepository.save(cart);
        orderRepository.save(order);
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
