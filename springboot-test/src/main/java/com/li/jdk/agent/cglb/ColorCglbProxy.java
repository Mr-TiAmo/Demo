package com.li.jdk.agent.cglb;

import com.li.design.proxy.White;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @program: demo
 * @description:    模式
 *                  优点
 *                  缺点
 * @author: li
 * @create: 2021-09-30 10:05
 **/
public class ColorCglbProxy implements MethodInterceptor {

    private Object target;

    public Object bind(Object obj) {
        this.target = obj;
        //通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        //设置enhancer对象的父类
        enhancer.setSuperclass(obj.getClass());
        // 绑定当前 代理类(持有被代理对象)
        enhancer.setCallback(this);
        //创建代理对象
        return enhancer.create();
    }

    /**
     *
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
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
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "F:\\class");
        ColorCglbProxy cglibProxy = new ColorCglbProxy();
        White bind = (White)cglibProxy.bind(new White());
        bind.print();
    }
}
