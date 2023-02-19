package com.shop.library.service;

import com.shop.library.dto.CustomerDto;
import com.shop.library.model.Customer;

public interface CustomerService {
    CustomerDto save(CustomerDto customerDto);

    Customer findByUsername(String username);

    Customer saveInfo(Customer customer);
}
