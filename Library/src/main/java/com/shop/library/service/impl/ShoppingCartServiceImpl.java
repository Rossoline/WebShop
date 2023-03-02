package com.shop.library.service.impl;

import com.shop.library.model.CartItem;
import com.shop.library.model.Customer;
import com.shop.library.model.Product;
import com.shop.library.model.ShoppingCart;
import com.shop.library.repository.CartItemRepository;
import com.shop.library.repository.ShoppingCartRepository;
import com.shop.library.service.ShoppingCartService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final CartItemRepository itemRepository;
    private final ShoppingCartRepository cartRepository;

    public ShoppingCartServiceImpl(CartItemRepository itemRepository,
                                   ShoppingCartRepository cartRepository){
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public ShoppingCart addItemToCart(Product product, int quantity, Customer customer){
        ShoppingCart cart = customer.getShoppingCart();
//        cart.getCartItems().stream().map(CartItem :: getQuantity).count();
//        cart.getCartItems().stream().map(CartItem :: getTotalPrice).count();
        if(cart == null){
            cart = new ShoppingCart();
        }
        Set<CartItem> cartItems = cart.getCartItems();
        CartItem cartItem = findCartItem(cartItems, product.getId());
        if(cartItems == null){
            cartItems = new HashSet<>();
            if(cartItem == null){
                cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setTotalPrice(quantity * product.getCostPrice());
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                cartItems.add(cartItem);
                itemRepository.save(cartItem);
            }
        }else{
            if(cartItem == null){
                cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setTotalPrice(quantity * product.getCostPrice());
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                cartItems.add(cartItem);
                itemRepository.save(cartItem);
            }else{
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                cartItem.setTotalPrice(cartItem.getTotalPrice() + quantity * product.getCostPrice());
                itemRepository.save(cartItem);
            }
        }
        cart.setCartItems(cartItems);
        cart.setTotalPrice(totalPrices(cart.getCartItems()));
        cart.setTotalItems(totalItems(cart.getCartItems()));
        cart.setCustomer(customer);
        return cartRepository.save(cart);
    }

    @Override
    public ShoppingCart updateItemCart(Product product, int quantity, Customer customer){
        ShoppingCart cart = customer.getShoppingCart();
        Set<CartItem> cartItems = cart.getCartItems();
        CartItem item = findCartItem(cartItems, product.getId());
        item.setQuantity(quantity);
        item.setTotalPrice(quantity * product.getCostPrice());
        itemRepository.save(item);
        int totalItems = totalItems(cartItems);
        double totalPrice = totalPrices(cartItems);
        cart.setTotalItems(totalItems);
        cart.setTotalPrice(totalPrice);
        return cartRepository.save(cart);
    }

    @Override
    public ShoppingCart deleteItemFromCart(Product product, Customer customer){
        ShoppingCart cart = customer.getShoppingCart();
        Set<CartItem> cartItems = cart.getCartItems();
        CartItem item = findCartItem(cartItems, product.getId());
        cartItems.remove(item);
        itemRepository.delete(item);
        double totalPrice = totalPrices(cartItems);
        int totalItems = totalItems(cartItems);
        cart.setCartItems(cartItems);
        cart.setTotalItems(totalItems);
        cart.setTotalPrice(totalPrice);
        return cartRepository.save(cart);
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

    private int totalItems(Set<CartItem> cartItems){
        int totalItems = 0;
        for(CartItem item : cartItems){
            totalItems += item.getQuantity();
        }
        return totalItems;
    }

    private double totalPrices(Set<CartItem> cartItems){
        double totalPrice = 0.0;
        for(CartItem item : cartItems){
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }
}
