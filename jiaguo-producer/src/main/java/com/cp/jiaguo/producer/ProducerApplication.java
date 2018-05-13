package com.cp.jiaguo.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties
public class ProducerApplication implements ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(ProducerApplication.class);
    private static final String LAST_ID_KEY = "lastId";
    private static ApplicationContext context;
    private static Config config;

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> saveLastId()));
    }

    public static void loadLastId() {
        try {
            String path = config.getLastIdFile();
            if (!Files.exists(Paths.get(path))) {
                log.error("File for lastId not exist - {}", path);
                return;
            }
            Properties properties = new Properties();
            try (InputStream stream = new FileInputStream(path)) {
                properties.load(stream);
            }
            String lastId = properties.getProperty(LAST_ID_KEY, "0");
            log.debug("last id loaded with value {}", lastId);
            config.setLastId(Integer.parseInt(lastId));
        } catch (Exception ex) {
            log.error("Error when loading last id.", ex);
        }
    }

    public static void saveLastId() {
        log.debug("saving the last id now.");
        try {
            String path = config.getLastIdFile();
            if (!Files.exists(Paths.get(path))) {
                log.error("File for lastId not exist - {}", path);
                return;
            }
            Properties properties = new Properties();
            try (InputStream stream = new FileInputStream(path)) {
                properties.load(stream);
            }
            properties.setProperty(LAST_ID_KEY, String.valueOf(config.getLastId()));
            try (OutputStream stream = new FileOutputStream(path)) {
                properties.store(stream, null);
            }
            log.debug("last id saved in {} with value {}", path, config.getLastId());
        } catch (Exception ex) {
            log.error("Error when saving last id.", ex);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        config = context.getBean(Config.class);
    }
}
