package com.li.jdk.clone;

import com.esotericsoftware.kryo.Kryo;
import lombok.Data;

/**
 * @program: demo
 * @description: 浅拷贝 深拷贝
 * 总结： 1)、序列化性能 Clone > new > Kryo序列化 > Jdk序列化 > Json(各种Json类似)序列化
 * 2)、Clone深拷贝性能最高，但是如果属性中有特定的对象字段，则需要自己编写代码
 * 3)、new 性能仅次于Clone，因为需要执行Jvm过程（常量池判断，内存分配，值初始化，init方法调用，栈中对象的引用等），并且主要是每个对象需要单独编写代码，当然也不建议使用反射
 * 4)、kryo 性能较高，并且不需要单独的开发， 若对性能不是特别高，可以考虑使用.(kryo是非线程安全的，项目中使用时可以放入ThreadLocal中)
 * 5)、Jdk序列化和Json序列化，性能太低，高性能项目不建议使用
 *
 * <dependency>
 * <groupId>com.esotericsoftware</groupId>
 * <artifactId>kryo</artifactId>
 * <version>4.0.1</version>
 * </dependency>
 * @author: li
 * @create: 2021-11-05 14:12
 **/
@Data
public class Student implements Cloneable {
    private String name;
    private Address address;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    @Override
    public Object clone() {
        Student student = null;
        try {
            student = (Student) super.clone();
            student.setAddress((Address) address.clone());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }

    public static void main(String[] args) {
        /**
         * ----------张三
         * ----------张三
         * ----------李四
         * ----------李四
         */
//        Student student1 = new Student("张三");
//        System.out.println("----------" + student1.getName());
//        Student student2 = student1;
//        System.out.println("----------" + student2.getName());
//        student2.setName("李四");
//        System.out.println("----------" + student1.getName());
//        System.out.println("----------" + student2.getName());


        /**
         * 重写 clone方法需要 实现Cloneable接口  浅拷贝
         */
//        Student student1 = new Student("张三");
//        System.out.println("----------" + student1.getName());
//        Student student2 = (Student) student1.clone();
//        System.out.println("----------" + student2.getName());
//        student2.setName("李四");
//        System.out.println("----------" + student1.getName());
//        System.out.println("----------" + student2.getName());

        /**
         *----------张三 :杭州市
         * ----------张三 :杭州市
         * ----------张三 :杭州市
         * ----------李四 :杭州市
         * ----------张三 :西湖区
         * ----------李四 :西湖区
         */
//        Student student1 = new Student("张三");
//        Address address = new Address("杭州市");
//        student1.setAddress(address);
//        System.out.println("----------" + student1.getName() + " :" +student1.getAddress().getAddress());
//        Student student2 = (Student) student1.clone();
//        System.out.println("----------" + student2.getName() + " :" +student2.getAddress().getAddress());
//        student2.setName("李四");
//        System.out.println("----------" + student1.getName() + " :" +student1.getAddress().getAddress());
//        System.out.println("----------" + student2.getName() + " :" +student2.getAddress().getAddress());
//
//        address.setAddress("西湖区");
//        System.out.println("----------" + student1.getName() + " :" +student1.getAddress().getAddress());
//        System.out.println("----------" + student2.getName() + " :" +student2.getAddress().getAddress());


        /**
         * 深拷贝
         * ----------张三 :杭州市
         * ----------张三 :杭州市
         * ----------张三 :杭州市
         * ----------李四 :杭州市
         * ----------张三 :西湖区
         * ----------李四 :杭州市
         */
        Student student1 = new Student("张三");
        Address address = new Address("杭州市");
        student1.setAddress(address);
        System.out.println("----------" + student1.getName() + " :" + student1.getAddress().getAddress());
        Student student2 = (Student) student1.clone();
        System.out.println("----------" + student2.getName() + " :" + student2.getAddress().getAddress());
        student2.setName("李四");
        System.out.println("----------" + student1.getName() + " :" + student1.getAddress().getAddress());
        System.out.println("----------" + student2.getName() + " :" + student2.getAddress().getAddress());

        address.setAddress("西湖区");
        System.out.println("----------" + student1.getName() + " :" + student1.getAddress().getAddress());
        System.out.println("----------" + student2.getName() + " :" + student2.getAddress().getAddress());

        Kryo kryo = new Kryo();
        Student copy = kryo.copy(student1);


    }
}
