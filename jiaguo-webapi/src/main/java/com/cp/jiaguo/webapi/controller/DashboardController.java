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

import java.util.HashMap;
import java.util.Map;

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
        DashboardModel model = getOrSetCache();
        return model;
    }

    private synchronized DashboardModel getOrSetCache() {
        DashboardModel model = new DashboardModel();
        Jedis jedis = getJedis();
        if (jedis != null) {
            Map<String, String> map = jedis.hgetAll(CACHE_KEY);
            if (map != null && !map.isEmpty()) {
                model.setProductCount(Long.valueOf(map.get("productCount")));
                model.setUserCount(Long.valueOf(map.get("userCount")));
                model.setCache(true);
            } else {
                refreshModel(model);
                map = new HashMap<>();
                map.put("productCount", String.valueOf(model.getProductCount()));
                map.put("userCount", String.valueOf(model.getUserCount()));
                jedis.hmset(CACHE_KEY, map);
                jedis.expire(CACHE_KEY, 120);
            }
            jedis.close();
        } else {
            refreshModel(model);
        }
        return model;
    }

    private Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (Exception ex) {
            log.error("Failed to get resource from Jedis pool.", ex);
        }
        return jedis;
    }

    private void refreshModel(DashboardModel model) {
        model.setProductCount(productService.getCount());
        model.setUserCount(userService.getCount());
        model.setCache(false);
    }
}
