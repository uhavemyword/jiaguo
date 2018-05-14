package com.cp.jiaguo.webapi.dao;

import com.cp.jiaguo.webapi.model.Product;
import com.cp.jiaguo.webapi.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDao extends EntityDao<Product> {
    List<Product> getTop(int count);
    List<Product> getRandom(int count);
    long getCount();
}
