package com.shop.library.service.impl;

import com.shop.library.model.CartItem;
import com.shop.library.repository.CartItemRepository;
import com.shop.library.repository.OrderRepository;
import com.shop.library.repository.ShoppingCartRepository;
import com.shop.library.service.CartItemService;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    private final ShoppingCartRepository shoppingCartService;

    private final OrderRepository orderService;

    public CartItemServiceImpl(CartItemRepository cartItemRepository,
                               ShoppingCartRepository shoppingCartService,
                               OrderRepository orderService){
        this.cartItemRepository = cartItemRepository;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
    }

    @Override public CartItem save(CartItem cartItem){
        return cartItemRepository.save(cartItem);
    }

    @Override public void delete(CartItem cartItem){
        cartItemRepository.delete(cartItem);
    }
}
