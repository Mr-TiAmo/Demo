package com.li.comm.config;

import org.springframework.core.io.ClassPathResource;
import org.apache.log4j.PropertyConfigurator;

import java.util.Properties;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2021-04-12 16:26
 **/
public class InitLogRecord {
    public static void initLog() {
        try {
            Properties properties = new Properties();
            ClassPathResource resource = new ClassPathResource("log4j.properties");
            properties.load( resource.getInputStream());
            PropertyConfigurator.configure(properties);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
