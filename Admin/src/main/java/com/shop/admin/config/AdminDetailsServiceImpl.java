package com.shop.admin.config;

import com.shop.library.model.Admin;
import com.shop.library.model.enums.Role;
import com.shop.library.repository.AdminRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AdminDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Admin admin = adminRepository.findByUserName(username);
        if(admin == null){
            throw new UsernameNotFoundException("Could not find username");
        }
        return new User(
                admin.getUserName(),
                admin.getPassword(),
                List.of(new SimpleGrantedAuthority(Role.ADMIN.toString())));
    }
}
