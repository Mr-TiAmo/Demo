package com.li.spring.register;

import com.li.spring.bean.Car;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @program: springboot-demo
 * @description: 实现ImportBeanDefinitionRegistrar接口的registerBeanDefinitions(),向IOC容器注入bean
 * @author: 栗翱
 * @create: 2020-06-17 22:50
 **/
public class MyImportRegister implements ImportBeanDefinitionRegistrar {
    /**
     *
     * @param importingClassMetadata 当前类的注解信息
     * @param registry  BeanDefinition 注册类
 *                          把所有需要注入IOC容器中的bean 通过调用BeanDefinitionRegistry.registerBeanDefinition()
     *                      手动向IOC注入bean
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean personA = registry.containsBeanDefinition("personA");
        //符合条件添加指定bean
        if (personA) {
            //定义bean信息，包括类型，scope...
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Car.class);
//            beanDefinition.setScope();
            // beanName 为我们自定义的名字， 不再是全类名
            registry.registerBeanDefinition("car", beanDefinition);
        }
    }
}
