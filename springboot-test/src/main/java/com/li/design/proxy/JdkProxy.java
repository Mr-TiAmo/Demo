package com.li.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: Demo
 * @description: jdk动态代理
 * @author: li
 * @create: 2020-07-29 09:05
 **/

public class JdkProxy implements InvocationHandler {

    private Object object;

    public Object bind(Object obj) {
        this.object = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("被代理方法执行之前，切入的代码");
        Object result = method.invoke(object, args);
        System.out.println("被代理方法执行之后，切入的代码");

        return null;
    }

    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy();
        Black black = new Black();
        Color bind = (Color)jdkProxy.bind(black);
        bind.print();
    }
}
