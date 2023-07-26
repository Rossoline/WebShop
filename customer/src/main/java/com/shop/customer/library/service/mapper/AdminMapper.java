package com.shop.customer.library.service.mapper;

import com.shop.customer.library.dto.AdminDto;
import com.shop.customer.library.model.Admin;
import com.shop.customer.library.model.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper implements RequestDtoMapper<AdminDto, Admin> {
    @Override
    public Admin mapToModel(AdminDto adminDto) {
        Admin admin = new Admin();
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setUserName(adminDto.getUsername());
        admin.setPassword(adminDto.getPassword());
        admin.setRole(Role.ADMIN);
        return admin;
    }
}
