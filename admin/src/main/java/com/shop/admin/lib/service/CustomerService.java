package com.shop.admin.lib.service;

import com.shop.admin.lib.dto.CustomerDto;
import com.shop.admin.lib.model.Customer;
import com.shop.admin.lib.model.ShoppingCart;

public interface CustomerService {
    Customer save(CustomerDto customerDto);

    Customer findByUsername(String username);

    Customer saveInfo(Customer customer);

    Customer findCustomerByCart(ShoppingCart cart);
}
