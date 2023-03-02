package com.shop.library.service.impl;

import com.shop.library.dto.AdminDto;
import com.shop.library.model.Admin;
import com.shop.library.model.Role;
import com.shop.library.repository.AdminRepository;
import com.shop.library.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin findByUsername(String userName){
        return adminRepository.findByUserName(userName);
    }

    @Override
    public Admin save(AdminDto adminDto){
        Admin admin = new Admin();
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setUserName(adminDto.getUsername());
        admin.setPassword(adminDto.getPassword());
        admin.setRole(Role.ADMIN);
        return adminRepository.save(admin);
    }
}
