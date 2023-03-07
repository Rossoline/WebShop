package com.shop.library.service.mapper;

import com.shop.library.dto.CustomerDto;
import com.shop.library.model.Customer;
import com.shop.library.model.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements RequestDtoMapper<CustomerDto, Customer> {
    @Override
    public Customer mapToModel(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setUserName(customerDto.getUserName());
        customer.setPassword(customerDto.getPassword());
        customer.setRole(Role.CUSTOMER);
        return customer;
    }
}
