package com.li.spring.factorybean;

import com.li.spring.bean.Color;
import org.springframework.beans.factory.FactoryBean;

/**
 * @program: springboot-demo
 * @description:  创建一个spring提供的FactoryBean
 * @author: 栗翱
 * @create: 2020-06-17 23:57
 **/

public class ColorFactoryBean implements FactoryBean<Color> {
    /**
     *
     * @return 返回要注入IOC容器的bean对象，这个对象会添加到IOC中
     * @throws Exception
     */
    @Override
    public Color getObject() throws Exception {
        System.out.println("创建对象，向IOC 中注入该对象");
        return new Color();
    }

    /**
     * @return 返回需要注入IOC容器的bean的类型
     */
    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    /**
     *   false 多实例
     *   ture 单实例
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
