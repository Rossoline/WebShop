package com.shop.library.service.mapper;

import com.shop.library.dto.AdminDto;
import com.shop.library.model.Admin;
import com.shop.library.model.enums.Role;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper implements RequestDtoMapper<AdminDto, Admin> {
    @Override
    public Admin mapToModel(AdminDto adminDto){
        Admin admin = new Admin();
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setUserName(adminDto.getUsername());
        admin.setPassword(adminDto.getPassword());
        admin.setRole(Role.ADMIN);
        return admin;
    }
}
