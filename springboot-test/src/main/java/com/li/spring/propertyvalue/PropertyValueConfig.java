package com.li.spring.propertyvalue;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @program: springboot-demo
 * @description: @PropertySource将配置中的值加载到运行环境中
 * @author: 栗翱
 * @create: 2020-06-22 22:15
 **/
@PropertySource(value = {"classpath:/animal.properties"})
@Configuration
public class PropertyValueConfig {

    @Bean
    public Animal animal() {
        return new Animal();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PropertyValueConfig.class);
        Animal animal = context.getBean(Animal.class);
        System.out.println(animal);

        ConfigurableEnvironment environment = context.getEnvironment();
        String type = environment.getProperty("type");
        System.out.println(type);
    }
}
