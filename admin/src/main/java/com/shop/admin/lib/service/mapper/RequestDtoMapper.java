package com.shop.admin.lib.service.mapper;

public interface RequestDtoMapper<D, M> {
    M mapToModel(D dto);
}
