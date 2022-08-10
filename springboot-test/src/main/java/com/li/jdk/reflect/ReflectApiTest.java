package com.li.jdk.reflect;

import com.li.jdk.base.Man;
import com.li.jdk.base.OverLoad;
import com.li.jdk.base.ReflectBean;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-08-10 09:59
 **/
public class ReflectApiTest {

    public ReflectApiTest() {
    }


    public static void main(String[] args) throws Exception {
        ReflectApiTest reflectApiTest = new ReflectApiTest();
//        reflectApiTest.getClassDemo();
//        reflectApiTest.constructorDemo();
//        reflectApiTest.fieldDemo();
        reflectApiTest.methodDemo();

    }

    /**
     * 获取类的class实例
     * 调用运行时类的属性：.class 若已知具体的类，通过类的class属性获取，该方法最为安全可靠， 程序性能最高
     * 通过运行时类的对象,调用getClass() , 改对象已实例化
     * 调用Class的静态方法：forName(String classPath) 已知一个类的全类名
     * 使用类的加载器：ClassLoader
     */
    public void getClassDemo() throws Exception {
        Class<String> stringClass = String.class;


        ReflectApiTest test = new ReflectApiTest();
        Class<? extends ReflectApiTest> aClass = test.getClass();


        Class<?> forName = Class.forName("com.li.jdk.reflect.ReflectApiTest");

        ClassLoader classLoader = this.getClass().getClassLoader();
        Class<?> apiTest = classLoader.loadClass("com.li.jdk.reflect.ReflectApiTest");
    }


    public void constructorDemo() throws Exception {
        Class<ReflectBean> aClass = ReflectBean.class;
        // 获取所有public修饰的构造器, 其他修饰符修饰的 构造器获取不到
        for (Constructor<?> constructor : aClass.getConstructors()) {
            System.out.println(constructor);
        }
        // 获取所有的构造器
        for (Constructor<?> constructor : aClass.getDeclaredConstructors()) {
            System.out.println(constructor);
            System.out.println(constructor.getModifiers());
            System.out.println(constructor.getName());
            System.out.println(Arrays.toString(constructor.getParameterTypes()));
        }


        // 获取指定参数类型的构造器
        Constructor<ReflectBean> constructor = aClass.getDeclaredConstructor(String.class);
        System.out.println(constructor);
        ReflectBean reflectBean = constructor.newInstance("张三");
        System.out.println(reflectBean);


        Constructor<ReflectBean> privateConstructor = aClass.getDeclaredConstructor(Integer.class, String.class);
        // 设置当前属性是可访问的
        privateConstructor.setAccessible(true);
        ReflectBean bean = privateConstructor.newInstance(20, "李四");
        System.out.println(bean);
    }

    public void fieldDemo() throws Exception {
        Class<ReflectBean> aClass = ReflectBean.class;
        // 获取所有public修饰的属性字段, 其他修饰符修饰的 属性字段获取不到
        for (Field field : aClass.getFields()) {
            System.out.println(field);
        }
        // 获取所有的属性字段
        for (Field field : aClass.getDeclaredFields()) {
            System.out.println(field);
            // 获取当前属性的 修饰符, 以整数形式返回
            System.out.println(field.getModifiers() + "---" + Modifier.toString(field.getModifiers()));
            // 获取当前属性类型
            System.out.println(field.getType());
            // 获取当前属性名称
            System.out.println(field.getName());

        }


        // 通过反射创建实例，并给属性赋值， 通过Class 直接newInstance，只能调用无参构造器，JavaBean需要有无参构造器
        ReflectBean reflectBean = aClass.newInstance();
        Field age = aClass.getDeclaredField("age");
        // 设置当前属性是可访问的
        age.setAccessible(true);
        age.set(reflectBean, 18);
        System.out.println(reflectBean);
    }


    public void methodDemo() throws Exception {
        Class<ReflectBean> aClass = ReflectBean.class;
        //返回此Class对象所表示的类或接口的public的方法
        for (Method method : aClass.getMethods()) {
            System.out.println(method);
        }
        //返回此Class对象所表示的类或接口的全部方法
        for (Method declaredMethod : aClass.getDeclaredMethods()) {
            System.out.println(declaredMethod);
            // 取得方法返回值类型
            System.out.println(declaredMethod.getReturnType());
            // 获取方法参数类型列表
            System.out.println(Arrays.toString(declaredMethod.getParameterTypes()));
            // 获取方法修饰符
            System.out.println(declaredMethod.getModifiers() + "---" + Modifier.toString(declaredMethod.getModifiers()));
            // 获取方法抛出异常类型列表
            System.out.println(Arrays.toString(declaredMethod.getExceptionTypes()));
        }


        // 通过反射创建实例，并调用指定方法
        ReflectBean reflectBean = aClass.newInstance();
        reflectBean.setName("测试");
        reflectBean.setAge(18);
        // public 无参
        Method test1 = aClass.getDeclaredMethod("test1");
        test1.invoke(reflectBean);

        // public 有参
        Method test2 = aClass.getDeclaredMethod("test2", ReflectBean.class);
        Object result = test2.invoke(reflectBean, reflectBean);
        System.out.println(result);

        // private
        Method test3 = aClass.getDeclaredMethod("test3", ReflectBean.class);
        test3.setAccessible(true);
        test3.invoke(reflectBean, reflectBean);

        //静态方法
        Method test4 = aClass.getDeclaredMethod("test4", ReflectBean.class);
        test4.setAccessible(true);
        test4.invoke(ReflectBean.class, reflectBean);

    }
}
