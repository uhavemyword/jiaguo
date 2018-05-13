package com.cp.jiaguo.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ResultHandler {
    private static final Logger log = LoggerFactory.getLogger(ResultHandler.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(String message) {
        JSONObject jsonObject = null;
        Integer id;
        try {
            jsonObject = JSON.parseObject(message);
            id = jsonObject.getJSONObject("product").getInteger("productId");
        } catch (Exception ex) {
            log.error("Received message is not in expected JSON format. Message: {}", message, ex);
            return;
        }

        jdbcTemplate.update("insert into product(external_id, info_json) values(?,?)", id, message);
        log.debug("Product with external_id {} saved in database.", id);
    }
}
