package com.shop.customer.config;

import com.shop.library.model.Customer;
import com.shop.library.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.stream.Collectors;

public class CustomerServiceConfig implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUserName(userName);
        if (customer == null) {
            throw new UsernameNotFoundException("Could not find User Name");
        }
        return new User(customer.getUserName(),
                customer.getPassword(),
                customer.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList()));
    }
}
