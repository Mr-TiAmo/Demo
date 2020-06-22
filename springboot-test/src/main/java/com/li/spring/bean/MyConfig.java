package com.li.spring.bean;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @program: springboot-demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-15 22:42
 **/
@Component
//告诉spring，这是一个配置类
@Configuration
public class MyConfig {

    // 给容器中注册一个bean， 类型为返回值类型，ID 默认为方法名称, 通过value修改 bean的名字
    @Bean(value = "person01")
    /**
     * singleton 单实例 默认  IOC容器启动会调用发放创建对象放入IOC容器中 AnnotationConfigApplicationContext context
     *                          以后获取bean对象，直接从IOC容器中拿取
     * prototype 多实例       IOC容器启动并不会调用方法创建对象，而是在获取对象的时候才会调用方法创建对象，获取一次，创建一次
     * request   同一次请求产生一个实例
     * session   同一个session产生一个实例
     *
     *
     * @Lazy懒加载模式   单实例 默认情况下会IOC容器启动的时候创建对象，懒加载容器启动不创建对象，获取对象时才创建对象，并进行初始化
     */
//    @Scope("prototype")
    @Scope("singleton")
    @Lazy
    public Person person() {
        System.out.println("给容器添加bean对象");
        return new Person("张三",18);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
//        //根据类型获取对象
//        Person person = context.getBean(Person.class);
//        //获取ioc容器中存在的bean 名字
//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//        System.out.println(person);
//        for (String name : beanDefinitionNames) {
//            System.out.println(name);
//        }
//        //根据类型获取bean 名字
//        String[] beanNamesForType = context.getBeanNamesForType(Person.class);
//        for (String s : beanNamesForType) {
//            System.out.println(s);
//        }
//
        System.out.println("》》》》》》》》》》》》》》》》》》》》》》》》》》单实例");
        System.out.println("IOC容器创建完成");
        Object person01 = context.getBean("person01");
        Object person02 = context.getBean("person01");
        System.out.println(person01 == person02);
    }
}
