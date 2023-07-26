package com.shop.customer.library.service.mapper;

public interface ResponseDtoMapper<D, M> {
    D mapToDto(M model);
}
