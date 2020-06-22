package com.li.spring.autowire;

import com.li.spring.bean.Person;
import com.li.spring.scan.BookDao;
import com.li.spring.scan.BookService;
import com.li.spring.scan.Plate;
import org.springframework.context.annotation.*;

/**
 * @program: springboot-demo
 * @description:    一：@Autowired 自动注入 spring定义的：
 *                      1.默认优先按照类型注入
 *                      2.如果找到多个相同类型组件，再将属性的名称当做组件的id去查找
 *                      3.如果使用@Qualifier() 指定需要装配的组件id
 *                      4.默认情况下 属性的值必须 赋好值否则会报错，使用@Autowired(required = false) 在找不到的情况 下不装配
 *                      5.使用@Primary()的组件，在发现多个相同类型组件的时候，会优先装配
 *                          同时在@Qualifier() 也存在的情况下， 会状态@Qualifier() 指定的组件
 *                  二：spring还支持Java规范的 @Resource和@Inject注解
 *                      1.@Resource和@autowire一样支持自动装配，默认按照组件名称进行装配
 *                          不支持@primary注解，不支持@autowire(required = false)功能
 *                      2.@inject 需要导入javax.inject包，
 *                          支持@primary注解，不支持@autowire(required = false)功能
 * @author: 栗翱
 * @create: 2020-06-22 22:15
 **/
@Configuration
@ComponentScan(value = "com.li.spring.scan")
public class AutowireConfig {

    @Primary()
    @Bean("plate2")
    public Plate plate() {
        return new Plate("2");
    }

    @Bean
    public Person person() {
        return new Person();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutowireConfig.class);
        Person person = context.getBean(Person.class);
        System.out.println(person);
        BookService bookService = context.getBean(BookService.class);
        System.out.println(bookService);
        BookDao bookDao = context.getBean(BookDao.class);
        System.out.println(bookDao);
        Plate plate = context.getBean(Plate.class);
        System.out.println(plate);
    }
}
