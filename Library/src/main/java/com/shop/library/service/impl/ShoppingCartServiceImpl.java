package com.shop.library.service.impl;

import com.shop.library.model.CartItem;
import com.shop.library.model.Customer;
import com.shop.library.model.Product;
import com.shop.library.model.ShoppingCart;
import com.shop.library.repository.ShoppingCartRepository;
import com.shop.library.service.CartItemService;
import com.shop.library.service.ShoppingCartService;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemService cartItemService;
    private final ShoppingCartRepository cartRepository;

    public ShoppingCartServiceImpl(CartItemService cartItemService,
                                   ShoppingCartRepository cartRepository){
        this.cartItemService = cartItemService;
        this.cartRepository = cartRepository;
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart){
        return cartRepository.save(shoppingCart);
    }

    @Override
    public void saveEmpty(ShoppingCart shoppingCart){
        Set<CartItem> cartItems = new HashSet<>();
        shoppingCart.setCartItems(cartItems);
        cartRepository.save(shoppingCart);
    }

    @Override
    public void addItemToCart(Product product, int quantity, Customer customer){
        ShoppingCart cart = customer.getShoppingCart();
        if(cart.getCartItems().stream()
                .noneMatch(c -> c.getProduct().getId().equals(product.getId()))){
            Set<CartItem> cartItems = cart.getCartItems();
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItems.add(cartItem);
            cart.setCartItems(cartItems);
            cartItemService.save(cartItem);
            cartRepository.save(cart);
        }
    }

    @Override
    public ShoppingCart updateItemCart(Product product, int quantity, Customer customer){
        ShoppingCart cart = customer.getShoppingCart();
        Set<CartItem> cartItems = cart.getCartItems();
        CartItem item = findCartItem(cartItems, product.getId());
        item.setQuantity(quantity);
        cartItemService.save(item);
        return cartRepository.save(cart);
    }

    @Override
    public ShoppingCart deleteItemFromCart(Product product, Customer customer){
        ShoppingCart cart = customer.getShoppingCart();
        Set<CartItem> cartItems = cart.getCartItems();
        CartItem item = findCartItem(cartItems, product.getId());
        cartItems.remove(item);
        cart.setCartItems(cartItems);
        cartItemService.delete(item);
        return cartRepository.save(cart);
    }

    @Override
    public BigDecimal totalPrice(ShoppingCart shoppingCart){
        return shoppingCart.getCartItems().stream()
                .map(c -> BigDecimal.valueOf(c.getQuantity())
                        .multiply(c.getProduct().getCostPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<ShoppingCart> findAll(){
        return cartRepository.findAll();
    }

    private CartItem findCartItem(Set<CartItem> cartItems, Long productId){
        if(cartItems == null){
            return null;
        }
        CartItem cartItem = null;
        for(CartItem item : cartItems){
            if(item.getProduct().getId() == productId){
                cartItem = item;
            }
        }
        return cartItem;
    }
}
