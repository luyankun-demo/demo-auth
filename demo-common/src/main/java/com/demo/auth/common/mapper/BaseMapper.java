package com.demo.auth.common.mapper;

public interface BaseMapper<T> {

    void insert(T object);

    void update(T object);

    T getById(Integer id);
}
