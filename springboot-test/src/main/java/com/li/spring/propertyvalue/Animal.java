package com.li.spring.propertyvalue;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @program: springboot-demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-22 22:10
 **/
@Data
public class Animal {

    /**
     * 通过@Value 给对象属性赋值
     * 1.@Value(值)
     * 2.SPEL值 @Value(#{})
     * 3.@Value(${}) 获取配置文件中的值 结合@PropertySource 指定配置文件位置
     */
    @Value("Dog")
    private String name;
    @Value("#{20-2}")
    private Integer age;
    @Value("${type}")
    private String type;
}
