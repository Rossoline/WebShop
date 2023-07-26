package com.shop.customer.library.service.mapper;

public interface RequestDtoMapper<D, M> {
    M mapToModel(D dto);
}
