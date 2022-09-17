package com.li.jdk.base;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-08-10 10:43
 **/
public class ReflectBean {
    private Integer age;

    public String name;

    public ReflectBean() {
    }

    public ReflectBean(Integer age) {
        this.age = age;
    }

    public ReflectBean(String name) {
        this.name = name;
    }

    private ReflectBean(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void test1() throws Exception {
        System.out.println("test1...");
    }

    public ReflectBean test2(ReflectBean bean) {
        System.out.println("test2..." + bean);
        return bean;
    }

    private void test3(ReflectBean bean) {
        System.out.println("test3..." + bean);
    }

    public static void test4(ReflectBean bean) {
        System.out.println("test4..." + bean);
    }

    @Override
    public String toString() {
        return "ReflectBean{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
