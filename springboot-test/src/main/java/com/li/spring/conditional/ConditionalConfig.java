package com.li.spring.conditional;

import com.li.spring.bean.Person;
import com.li.spring.conditional.condition.ConditionA;
import com.li.spring.conditional.condition.ConditionB;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;

/**
 * @program: springboot-demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-16 22:57
 **/
@Configuration
public class ConditionalConfig {
    /**
     * 可以放在类上，符合条件 类中的bean 全部注册IOC， 放在方法上，只注册 当前bean 到IOC中
     * @conditional({Condition[]}): 按照某种条件，给IOC容器注册符合条件的bean
     * @return                      可以放入Condition[]数组
     */


    @Bean("personA")
    @Conditional({ConditionA.class})
    public Person personA() {
        return new Person("李四",20);
    }

    @Bean("personB")
    @Conditional({ConditionB.class})
    public Person personB() {
        return new Person("张三",18);
    }

    @Bean("personC")
    @Conditional({ConditionB.class})
    public Person personC() {
        return new Person("六子",30);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionalConfig.class);

        System.out.println("》》》》》》》》》》》》》》》》》》》》》》》》》》");
        Map<String, Person> beansOfType = context.getBeansOfType(Person.class);
        System.out.println(beansOfType);

        System.out.println("》》》》》》》》》》》》》》》》》》》》》》》》》》");
        //获取系统环境
        ConfigurableEnvironment environment = context.getEnvironment();
        String name = environment.getProperty("os.name");
        System.out.println(name);
    }
}
