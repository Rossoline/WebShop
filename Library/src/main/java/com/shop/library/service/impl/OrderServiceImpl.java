package com.shop.library.service.impl;

import com.shop.library.model.CartItem;
import com.shop.library.model.Order;
import com.shop.library.model.OrderDetail;
import com.shop.library.model.OrderStatus;
import com.shop.library.model.ShoppingCart;
import com.shop.library.repository.CartItemRepository;
import com.shop.library.repository.OrderDetailRepository;
import com.shop.library.repository.OrderRepository;
import com.shop.library.repository.ShoppingCartRepository;
import com.shop.library.service.OrderService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final ShoppingCartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public OrderServiceImpl(OrderDetailRepository orderDetailRepository,
                            OrderRepository orderRepository,
                            ShoppingCartRepository cartRepository,
                            CartItemRepository cartItemRepository){
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public void save(ShoppingCart cart){
        Order order = new Order();
        order.setOrderStatus(OrderStatus.APPROVAL);
        order.setOrderDate(new Date());
        order.setCustomer(cart.getCustomer());
        order.setTotalPrice(cart.getTotalPrice());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for(CartItem item : cart.getCartItems()){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setQuantity(item.getQuantity());
            orderDetail.setProduct(item.getProduct());
            orderDetail.setUnitPrice(item.getProduct().getCostPrice());
            orderDetailRepository.save(orderDetail);
            orderDetailList.add(orderDetail);
            cartItemRepository.delete(item);
        }
        order.setOrderDetailList(orderDetailList);
        cart.setCartItems(new HashSet<>());
        cart.setTotalItems(0);
        cart.setTotalPrice(0);
        cartRepository.save(cart);
        orderRepository.save(order);
    }

    @Override
    public void acceptOrder(Long id){
        Order order = orderRepository.getById(id);
        order.setDeliveryDate(new Date());
        order.setOrderStatus(OrderStatus.DELIVERY);
        orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Long id){
        orderRepository.deleteById(id);
    }
}
