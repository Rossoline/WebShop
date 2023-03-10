package com.shop.library.service.impl;

import com.shop.library.model.CartItem;
import com.shop.library.repository.CartItemRepository;
import com.shop.library.service.CartItemService;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository){
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public CartItem save(CartItem cartItem){
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void delete(CartItem cartItem){
        cartItemRepository.delete(cartItem);
    }
}
