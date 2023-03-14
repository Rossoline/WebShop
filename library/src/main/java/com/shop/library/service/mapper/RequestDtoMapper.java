package com.shop.library.service.mapper;

public interface RequestDtoMapper<D, M> {
    M mapToModel(D dto);
}
