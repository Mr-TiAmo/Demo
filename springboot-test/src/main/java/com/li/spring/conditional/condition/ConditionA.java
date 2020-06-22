package com.li.spring.conditional.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @program: springboot-demo
 * @description:  自定义condition条件，需要实现Condition接口的 matches()
 * @author: 栗翱
 * @create: 2020-06-17 22:02
 **/
public class ConditionA implements Condition {
    /**
     *
     * @param context  判断条件能使用的上下文(环境)
     * @param metadata  注解信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //获取IOC 的beanFactory
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        //获取类加载器
        ClassLoader classLoader = context.getClassLoader();
        //获取系统环境
        Environment environment = context.getEnvironment();
        //获取到bean定义的注册类， 注册bean，移除bean，判断是否有bean
        BeanDefinitionRegistry registry = context.getRegistry();
        String os = environment.getProperty("os.name");
        if (os.contains("Windows")) {
            return true;
        }
        return false;
    }
}
