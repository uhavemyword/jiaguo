package com.cp.jiaguo.webapi.service;

import com.cp.jiaguo.webapi.model.BaseEntity;

import java.util.List;

public interface EntityService<E extends BaseEntity> {
    List<E> getAll();

    E getById(Integer id);

    void insert(E entity);

    void update(E entity);

    void deleteById(Integer id);
}