package com.cp.jiaguo.webapi.service;

import com.cp.jiaguo.webapi.model.Product;

import java.util.List;

public interface ProductService extends EntityService<Product> {
    List<Product> getTop(int count);
    List<Product> getRandom(int count);
}
