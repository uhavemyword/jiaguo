package com.cp.jiaguo.webapi.service.impl;

import com.cp.jiaguo.webapi.dao.ProductDao;
import com.cp.jiaguo.webapi.model.Product;
import com.cp.jiaguo.webapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends EntityServiceImpl<Product, ProductDao> implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getTop(int count) {
        return productDao.getTop(count);
    }

    @Override
    public List<Product> getRandom(int count) {
        return productDao.getRandom(count);
    }

    @Override
    public long getCount(){
        return productDao.getCount();
    }
}
