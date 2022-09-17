package com.li.jdk.overReadAndoverLoad.bean;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-08-11 17:43
 **/
public class Animal {

    public void print(Animal animal) {
        System.out.println("animal");
    }
    public void print(Bird bird) {
        System.out.println("bird");
    }
    public void print(Cat cat) {
        System.out.println("cat");
    }

    public void run() {
        System.out.println("animal run");
    }
}
