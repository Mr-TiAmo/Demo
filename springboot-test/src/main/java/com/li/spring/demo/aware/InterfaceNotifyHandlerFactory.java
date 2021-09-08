package com.li.spring.demo.aware;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: demo
 * @description: ApplicationContextAware 通过ApplicationContext上下文对象 从容器中获取bean，或者注入bean
 * @author: li
 * @create: 2021-09-08 15:29
 **/
@Component
public class InterfaceNotifyHandlerFactory implements ApplicationContextAware {

    /**
     * 获取指定bean
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }
    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

    /**
     * 注册bean
     * @param name
     * @param clazz
     * @param value
     */
    public static synchronized void setBean(String name, Class<?> clazz, Map<String, String> value) {
        DefaultListableBeanFactory factory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        if (factory.containsBean(name)) {
            return;
        }
        GenericBeanDefinition definition = new GenericBeanDefinition();
        definition.setBeanClass(clazz);
        definition.setPropertyValues(new MutablePropertyValues(value));
        factory.registerBeanDefinition(name,definition);
    }



    public static ApplicationContext applicationContext;
    public static Map<Long, InterfaceNotifyHandler> handlerMap = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        Map<String, InterfaceNotifyHandler> beansOfType = applicationContext.getBeansOfType(InterfaceNotifyHandler.class);
        for (Map.Entry<String, InterfaceNotifyHandler> handlerEntry : beansOfType.entrySet()) {
            if (CollectionUtils.isEmpty(handlerEntry.getValue().getIds())) {
                continue;
            }
            for (Long productId : handlerEntry.getValue().getIds()) {
                handlerMap.put(productId, handlerEntry.getValue());
            }
        }
    }

    public InterfaceNotifyHandler getQueryHandler(Long productId) {
        InterfaceNotifyHandler notifyHandler = null;
        if (productId != null) {
            notifyHandler = handlerMap.get(productId);
        }
        if (notifyHandler == null) {
            return (InterfaceNotifyHandler) applicationContext.getBean("defaultPolicyNotifyHandler");
        } else {
            return notifyHandler;
        }
    }
}
