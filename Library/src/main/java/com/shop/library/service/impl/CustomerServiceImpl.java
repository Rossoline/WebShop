package com.shop.library.service.impl;

import com.shop.library.dto.CustomerDto;
import com.shop.library.model.Customer;
import com.shop.library.repository.CustomerRepository;
import com.shop.library.repository.RoleRepository;
import com.shop.library.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private RoleRepository repository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setUserName(customerDto.getUserName());
        customer.setPassword(customerDto.getPassword());
        customer.setRoles(Arrays.asList(repository.findByName("CUSTOMER")));
        Customer customerSave = customerRepository.save(customer);
        return toDto(customerSave);
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findByUserName(username);
    }

    @Override
    public Customer saveInfo(Customer customer) {
        Customer customerForSave = customerRepository.findByUserName(customer.getUserName());
        customerForSave.setAddress(customer.getAddress());
        customerForSave.setCity(customer.getCity());
        customerForSave.setPhoneNumber(customer.getPhoneNumber());
        return customerRepository.save(customerForSave);
    }

    private CustomerDto toDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setUserName(customer.getUserName());
        customerDto.setPassword(customer.getPassword());
        return customerDto;
    }
}
