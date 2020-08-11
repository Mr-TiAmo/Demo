package com.li.design.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: Demo
 * @description:
 * @author: li
 * @create: 2020-07-30 19:24
 **/
public class CglibProxy implements MethodInterceptor {

    public Object bind(Object obj) {
        //创建加强器，用来设置生成代理对象的配置
        Enhancer enhancer = new Enhancer();
        //为加强器指定要代理的方法，（为下面的代理类指定父类）
        enhancer.setSuperclass(obj.getClass());
        //设置回调，对于代理类上的所有方法，都会调用Callback，而Callback的实现需要intercept()
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("被代理方法之前执行的代码");
        //invokeSuper 被代理方法的返回值
        Object invokeSuper = methodProxy.invokeSuper(o, objects);
        System.out.println("被代理方法之后执行的代码-----------"+invokeSuper);
        System.out.println(method.getName()+"---"+method.getClass());
        //这里可以对被调用方法返回值进行包装
        return invokeSuper;
    }

    public static void main(String[] args) {
        CglibProxy cglibProxy = new CglibProxy();
        White bind = (White)cglibProxy.bind(new White());
        bind.print();
        String test = bind.test();
        System.out.println(test);
    }
}
