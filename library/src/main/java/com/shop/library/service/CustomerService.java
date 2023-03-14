package com.shop.library.service;

import com.shop.library.dto.CustomerDto;
import com.shop.library.model.Customer;
import com.shop.library.model.ShoppingCart;

public interface CustomerService {
    Customer save(CustomerDto customerDto);

    Customer findByUsername(String username);

    Customer saveInfo(Customer customer);

    Customer findCustomerByCart(ShoppingCart cart);
}
