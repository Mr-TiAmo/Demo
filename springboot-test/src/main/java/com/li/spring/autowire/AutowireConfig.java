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
 *                          @Resource的作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按 byName自动注入罢了。
 *                          @Resource有两个属性是比较重要的，分是name和type，Spring将@Resource注解的name属性解析为bean的名字，
 *                          而type属性则解析为bean的类型。所以如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略。
 *                          如果既不指定name也不指定type属性，这时将通过反射机制使用byName自动注入策略。
 *  * 　　                   @Resource装配顺序
 *  * 　　                   1. 如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常
 *  * 　　                   2. 如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常
 *  * 　　                   3. 如果指定了type，则从上下文中找到类型匹配的唯一bean进行装配，找不到或者找到多个，都会抛出异常
 *  * 　　                   4. 如果既没有指定name，又没有指定type，则自动按照byName方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配；
 *                      2.@inject 需要导入javax.inject包，
 *                          支持@primary注解，不支持@autowire(required = false)功能
*
 *
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
