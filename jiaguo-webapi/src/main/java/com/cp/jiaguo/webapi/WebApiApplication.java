package com.cp.jiaguo.webapi;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@SpringBootApplication
@MapperScan("com.cp.jiaguo.webapi.dao")
@EnableTransactionManagement
public class WebApiApplication {
    @Autowired
    private ApplicationContext appContext;

    public static void main(String[] args) {
        SpringApplication.run(WebApiApplication.class, args);
    }

    @Bean
    public JedisPool jedisPool() {
        Environment env = appContext.getEnvironment();
        String jedisHost = env.getProperty("spring.redis.host");
        return new JedisPool(new JedisPoolConfig(), jedisHost);
    }
}
