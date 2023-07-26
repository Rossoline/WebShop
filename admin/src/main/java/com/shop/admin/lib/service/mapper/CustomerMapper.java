package com.shop.admin.lib.service.mapper;

import com.shop.admin.lib.dto.CustomerDto;
import com.shop.admin.lib.model.Customer;
import com.shop.admin.lib.model.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements RequestDtoMapper<CustomerDto, Customer> {
    @Override
    public Customer mapToModel(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setUserName(customerDto.getUserName());
        customer.setPassword(customerDto.getPassword());
        customer.setRole(Role.CUSTOMER);
        return customer;
    }
}
