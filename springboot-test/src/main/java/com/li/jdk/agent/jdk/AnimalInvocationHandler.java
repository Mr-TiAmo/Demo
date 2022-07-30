package com.li.jdk.agent.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * @program: demo
 * @description: JDK动态代理
 * *                模式     需要定义接口或者父类，被代理对象与代理对象一起实现相同的接口
 * *               优点     不改变已有类的情况下，进行扩展，在目标方法前后切入操作，只需要一个代理类，动态的代理实现相同接口的已有类
 * *               缺点     被代理对象必须实现接口
 * @author: li
 * @create: 2021-09-16 16:03
 **/
public class AnimalInvocationHandler implements InvocationHandler {

    private Object target;

    public Object bind(Object target) {
        this.target = target;
        /**
         * ClassLoader loader       定义由哪个classloader对 生成的代理类(Proxy.newProxyInstance()方法生成的)进行加载
         * Class<?>[] interfaces    提供一个接口对象数据，声明代理类可以调用接口中的方法
         * InvocationHandler h      表示当动态代理对象调用方法的时候关联到哪一个InvocationHandler上，并最终由其调用对应的invoke()
         */
        return Proxy.newProxyInstance(
                this.target.getClass().getClassLoader(),
                this.target.getClass().getInterfaces(),
                this);
    }


    /**
     * Proxy.newProxyInstance() 方法生成的 代理类 在执行invoke(), 会执行到被代理类的方法
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入代理\n");
        Object result = method.invoke(target, args);
        System.out.print("时间为:" + new Date() + "\n\n");
        return result;
    }


    public static void main(String[] args) throws Exception{
        //保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 被代理类
        Dog dog = new Dog();
        // 被代理类和 当前的InvocationHandler处理类进行绑定
        AnimalInvocationHandler invocationHandler = new AnimalInvocationHandler();
        // 真正的代理对象
        /**
         * ClassLoader loader       定义由哪个classloader对 生成的代理类(Proxy.newProxyInstance()方法生成的)进行加载
         * Class<?>[] interfaces    提供一个接口对象数据，声明代理类可以调用接口中的方法
         * InvocationHandler h      表示当动态代理对象调用方法的时候关联到哪一个InvocationHandler上，并最终由其调用对应的invoke()
         */
        Animal dogProxy = (Animal)invocationHandler.bind(dog);
        dogProxy.eat();
        dogProxy.run();
        Method run = Class.forName("com.li.jdk.agent.jdk.Animal").getMethod("run");
    }
}
