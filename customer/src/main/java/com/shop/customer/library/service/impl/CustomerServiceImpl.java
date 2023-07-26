package com.shop.customer.library.service.impl;

import com.shop.customer.library.dto.CustomerDto;
import com.shop.customer.library.model.Customer;
import com.shop.customer.library.model.ShoppingCart;
import com.shop.customer.library.repository.CustomerRepository;
import com.shop.customer.library.service.CustomerService;
import com.shop.customer.library.service.ShoppingCartService;
import com.shop.customer.library.service.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final ShoppingCartService shoppingCartService;

    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CustomerMapper customerMapper,
                               ShoppingCartService shoppingCartService) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public Customer save(CustomerDto customerDto) {
        ShoppingCart cart = new ShoppingCart();
        Customer customer = customerMapper.mapToModel(customerDto);
        customer.setShoppingCart(cart);
        shoppingCartService.save(cart);
        return customerRepository.save(customer);
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

    @Override
    public Customer findCustomerByCart(ShoppingCart cart) {
        return customerRepository.findCustomerByShoppingCart(cart);
    }
}
