package com.li.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Observer;

/**
 * @program: Demo
 * @description: jdk动态代理
 * @author: li
 * @create: 2020-07-29 09:05
 **/

public class JdkProxy implements InvocationHandler {

    private Object object;

    public Object bind(Object obj) {
        //newProxyInstance（ClassLoader loader,Class<?>[] interfaces,InvocationHandler h）
        //ClassLoader 定义了哪个ClassLoader对生成的被代理对象进行加载  obj.getClass().getClassLoader()得到被代理类的加载器
        //Class<?>[]  被代理类要是海鲜的全部接口  obj.getClass().getInterfaces()被代理类所包含的接口
        //this 指 代理类实现的InvocationHandler
        this.object = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("被代理方法执行之前，切入的代码");
        Object result = method.invoke(object, args);
        System.out.println("被代理方法执行之后，切入的代码");

        return result;
    }

    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy();
        Black black = new Black();
        Color bind = (Color)jdkProxy.bind(black);
        bind.print();

    }
}
