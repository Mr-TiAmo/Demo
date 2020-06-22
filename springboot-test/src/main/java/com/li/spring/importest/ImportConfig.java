package com.li.spring.importest;

import com.li.spring.bean.Color;
import com.li.spring.bean.Person;
import com.li.spring.register.MyImportRegister;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @program: springboot-demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-17 22:28
 **/
@Configuration
/**
 * @Import(Color.class) 导入一个组件， 默认bean的名称为 全类名 com.li.spring.bean.Color
 */
@Import({Color.class, MyImportRegister.class})
public class ImportConfig {
    @Bean("personA")
    public Person personA() {
        return new Person("李四",20);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImportConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String definitionName : beanDefinitionNames) {
            System.out.println(definitionName);
        }
    }

}
