package com.li.spring.importest;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @program: springboot-demo
 * @description: 自定义导入组件
 * @author: 栗翱
 * @create: 2020-06-17 22:34
 **/
public class MyImportSelector implements ImportSelector {
    /**
     *
     * @param importingClassMetadata 当前标注@Import 注解的类的所有注解信息
     * @return  返回值是注册到IOC容器中的符合条件的bean  全类名
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //返回值不能为null， 会报错
//        return new String[0];
        return new String[]{"com.li.spring.bean.Car"};
    }
}
