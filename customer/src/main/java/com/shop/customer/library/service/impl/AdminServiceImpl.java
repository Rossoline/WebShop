package com.shop.customer.library.service.impl;

import com.shop.customer.library.dto.AdminDto;
import com.shop.customer.library.model.Admin;
import com.shop.customer.library.repository.AdminRepository;
import com.shop.customer.library.service.AdminService;
import com.shop.customer.library.service.mapper.AdminMapper;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminRepository adminRepository, AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.adminMapper = adminMapper;
    }

    @Override
    public Admin save(AdminDto adminDto) {
        return adminRepository.save(adminMapper.mapToModel(adminDto));
    }

    @Override
    public Admin findByUsername(String userName) {
        return adminRepository.findByUserName(userName);
    }
}
