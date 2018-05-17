package com.cp.jiaguo.webapi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cp.jiaguo.webapi.model.Product;
import com.cp.jiaguo.webapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/productinfo", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductInfoController {

    @Autowired
    private ProductService service;

    @GetMapping("/count")
    public long getCount() {
        return service.getCount();
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Integer id) {
        Product product = this.service.getById(id);
        return product == null ? null : product.getInfoJson();
    }

    @GetMapping("/top/{count}")
    public List<JSONObject> getTop(@PathVariable("count") Integer count) {
        if (count < 1 || count > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Count too large or too small!");
        }
        List<Product> products = this.service.getTop(count);
        return parse(products);
    }

    @GetMapping("/random/{count}")
    public List<JSONObject> getRandom(@PathVariable("count") Integer count) {
        if (count < 1 || count > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Count too large or too small!");
        }
        List<Product> products = this.service.getRandom(count);
        return parse(products);
    }

    private List<JSONObject> parse(List<Product> products) {
        return products.stream().map(p -> JSON.parseObject(p.getInfoJson())).collect(Collectors.toList());
    }
}
