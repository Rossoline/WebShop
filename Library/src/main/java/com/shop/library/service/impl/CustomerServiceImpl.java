package com.shop.library.service.impl;

import com.shop.library.dto.CustomerDto;
import com.shop.library.model.Customer;
import com.shop.library.model.ShoppingCart;
import com.shop.library.model.enums.Role;
import com.shop.library.repository.CustomerRepository;
import com.shop.library.service.CustomerService;
import com.shop.library.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final ShoppingCartService shoppingCartService;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               ShoppingCartService shoppingCartService){
        this.customerRepository = customerRepository;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public CustomerDto save(CustomerDto customerDto){
        Customer customer = new Customer();
        ShoppingCart cart = new ShoppingCart();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setUserName(customerDto.getUserName());
        customer.setPassword(customerDto.getPassword());
        customer.setRole(Role.CUSTOMER);
        customer.setShoppingCart(cart);
        cart.setCustomer(customer);
        customerRepository.save(customer);
        shoppingCartService.save(cart);
        return customerDto;
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
}
