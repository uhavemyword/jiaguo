package com.cp.jiaguo.webapi.service.impl;

import com.cp.jiaguo.webapi.dao.EntityDao;
import com.cp.jiaguo.webapi.model.BaseEntity;
import com.cp.jiaguo.webapi.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EntityServiceImpl<E extends BaseEntity, D extends EntityDao<E>> implements EntityService<E> {
    @Autowired
    private D dao;

    @Override
    public List<E> getAll() {
        return this.dao.getAll();
    }

    @Override
    public E getById(Integer id) {
        return this.dao.getById(id);
    }

    @Override
    public void insert(E entity) {
        this.dao.insert(entity);
    }

    @Override
    public void update(E entity) {
        this.dao.update(entity);
    }

    @Override
    public void deleteById(Integer id) {
        this.dao.deleteById(id);
    }

    @Override
    public Integer getCount() {
        return this.dao.getCount();
    }
}
