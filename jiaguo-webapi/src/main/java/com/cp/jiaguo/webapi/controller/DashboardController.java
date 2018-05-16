package com.cp.jiaguo.webapi.controller;

import com.cp.jiaguo.webapi.model.DashboardModel;
import com.cp.jiaguo.webapi.service.ProductService;
import com.cp.jiaguo.webapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private static final Logger log = LoggerFactory.getLogger(DashboardController.class);
    private static final String CACHE_KEY = "productCount";

    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping("/demo")
    public DashboardModel getDemo() {

        DashboardModel model = new DashboardModel();
        model.setProductCount(Long.valueOf(getProductCountWithCache()));
        model.setUesrCount(userService.getCount());
        return model;
    }

    private String getProductCountWithCache() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (Exception ex) {
            log.error("Failed to get resource from Jedis pool.", ex);
        }

        if (jedis != null) {
            String value = jedis.get(CACHE_KEY);
            if (value == null) {
                value = productService.getCount().toString();
                jedis.setex(CACHE_KEY, 60, value);
            }
            return value;
        }
        return productService.getCount().toString();
    }
}
