package com.shop.customer.config;

import com.shop.customer.library.model.Customer;
import com.shop.customer.library.repository.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUserName(userName);
        if (customer == null) {
            throw new UsernameNotFoundException("Could not find User Name");
        }
        return new User(customer.getUserName(), customer.getPassword(),
                List.of(new SimpleGrantedAuthority(customer.getRole().toString())));
    }
}
