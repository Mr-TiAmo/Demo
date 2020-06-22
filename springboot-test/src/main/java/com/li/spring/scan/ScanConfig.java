package com.li.spring.scan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @program: springboot-demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-15 23:18
 **/
@Configuration
/**
 * @ComponentScan 默认扫描 指定包下的 @Controller @Repository @Service @Configuration（这些注解中都包含@Component ）
 * value 扫描指定包下  excludeFilters 过滤规则,排除指定项   includeFilters 只扫描指定项 需要设置 useDefaultFilters = false才生效
 * useDefaultFilters 默认扫描 指定包下所有组件 bean
 *
 * @ComponentScan  @Repeatable(ComponentScans.class)是一个可重复注解，可以出现多次
 *
 * @ComponentScans 指定多个 @ComponentScan 包扫描注解
 *
 * FilterType：
 *  ANNOTATION 根据注解扫描指定 的 注解组件
 *  ASSIGNABLE_TYPE  按照给定的类型扫描组件, 包括其子类以及实现类
 *  ASPECTJ  使用ASPECTJ表达式来扫描组件
 *  REGEX    使用正则表达式来扫描组件
 *  CUSTOM   使用自定义规则来扫描组件  需要实现TypeFilter接口 重写 match方法
 *           默认只会扫描到 ScanConfig类
 *
 *
 */
@ComponentScan(value = "com.li.spring.scan",
        includeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Service.class}),
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {BookDao.class}),
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyScanFilter.class})
        }, useDefaultFilters = false
//        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Repository.class, Service.class})}
)
//@ComponentScans(
//        value = {@ComponentScan(value = "com.li.spring.scan", includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,
//                classes = {Service.class})}, useDefaultFilters = false),
//                @ComponentScan(value = "com.li.spring.scan", includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,
//                        classes = {Service.class})}, useDefaultFilters = false)}
//)
public class ScanConfig {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ScanConfig.class);
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String definitionName : beanDefinitionNames) {
            System.out.println(definitionName);
        }
    }
}
