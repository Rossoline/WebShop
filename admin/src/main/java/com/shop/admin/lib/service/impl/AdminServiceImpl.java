package com.shop.admin.lib.service.impl;

import com.shop.admin.lib.dto.AdminDto;
import com.shop.admin.lib.model.Admin;
import com.shop.admin.lib.repository.AdminRepository;
import com.shop.admin.lib.service.AdminService;
import com.shop.admin.lib.service.mapper.AdminMapper;
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
