package com.shop.library.service.impl;

import com.shop.library.dto.CustomerDto;
import com.shop.library.model.Customer;
import com.shop.library.model.Role;
import com.shop.library.model.ShoppingCart;
import com.shop.library.repository.CustomerRepository;
import com.shop.library.repository.ShoppingCartRepository;
import com.shop.library.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               ShoppingCartRepository shoppingCartRepository){
        this.customerRepository = customerRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public CustomerDto save(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setUserName(customerDto.getUserName());
        customer.setPassword(customerDto.getPassword());
        customer.setRole(Role.CUSTOMER);
        ShoppingCart cart = new ShoppingCart();
        cart.setCustomer(customer);
        customerRepository.save(customer);
        shoppingCartRepository.save(cart);
        return toDto(customer);
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
