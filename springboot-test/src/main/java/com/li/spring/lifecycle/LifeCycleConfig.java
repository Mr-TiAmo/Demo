package com.li.spring.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springboot-demo
 * @description: bean的生命周期
 *                  bean创建--初始化--销毁
*                IOC容器管理bean的生命周期
 *                  我们可以自定义初始化和销毁方法，容器在bean进行到当前生命周期的时候
 *                  调用我们自定义的方法
 *
*                初始化：
 *                  单实例 在容器启动的时候 创建bean对象，并赋值， 调用初始化方法
 *                  多实例 在容器启动的时候不会创建对象，获取bean对象的时候才会创建对象，并赋值，调用初始化方法
*                销毁：
 *                  单实例  在容器关闭的时候调用销毁方法
 *                  多实例  容器不调用销毁方法
*                 1. @Bean(initMethod = "init", destroyMethod = "destroy")
 *                2. 通过让bean 实现InitializingBean(初始化), DisposableBean(销毁)
 *                3.JSR250 使用 @PostConstruct 在容器创建之后，创建bean对象并赋值，调用初始化
 *                          使用@PreDestroy 在容器关闭之后，调用销毁
 *                4. BeanPostProcessor[interface] bean的后置处理器
 *                      在bean初始化前后进行一些处理
 *                      postProcessBeforeInitialization 初始化之前
 *                      postProcessAfterInitialization 初始化之后
 * @author: 栗翱
 * @create: 2020-06-17 22:28
 **/
@ComponentScan("com.li.spring.lifecycle")
@Configuration
public class LifeCycleConfig {

//    @Scope("prototype")
//    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Bean
    public Red red() {
        return new Red();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        System.out.println("IOC容器创建完成");
        context.getBean("red");
        context.close();
    }

}
