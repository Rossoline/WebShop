package com.shop.customer.library.service;

import com.shop.customer.library.dto.CustomerDto;
import com.shop.customer.library.model.Customer;
import com.shop.customer.library.model.ShoppingCart;

public interface CustomerService {
    Customer save(CustomerDto customerDto);

    Customer findByUsername(String username);

    Customer saveInfo(Customer customer);

    Customer findCustomerByCart(ShoppingCart cart);
}
