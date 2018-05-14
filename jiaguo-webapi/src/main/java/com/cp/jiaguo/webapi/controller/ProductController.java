package com.cp.jiaguo.webapi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cp.jiaguo.webapi.model.Product;
import com.cp.jiaguo.webapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/count")
    public long getCount() {
        return service.getCount();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable("id") Integer id) {
        Product product = this.service.getById(id);
        return product;
    }

    @GetMapping("/top/{count}")
    public List<Product> getTop(@PathVariable("count") Integer count) {
        if (count < 1 || count > 1000) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Count too large or too small!");
        }
        List<Product> products = this.service.getTop(count);
        return products;
    }

    @GetMapping("/random/{count}")
    public List<Product> getRandom(@PathVariable("count") Integer count) {
        if (count < 1 || count > 1000) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Count too large or too small!");
        }
        List<Product> products = this.service.getRandom(count);
        return products;
    }
}
