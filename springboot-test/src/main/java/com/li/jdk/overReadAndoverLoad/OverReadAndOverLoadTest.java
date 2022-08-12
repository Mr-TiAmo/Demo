package com.li.jdk.overReadAndoverLoad;

import com.li.jdk.overReadAndoverLoad.bean.Animal;
import com.li.jdk.overReadAndoverLoad.bean.Bird;
import com.li.jdk.overReadAndoverLoad.bean.Cat;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-08-11 17:41
 **/
public class OverReadAndOverLoadTest {


    public static void main(String[] args) throws Exception{
        OverReadAndOverLoadTest test = new OverReadAndOverLoadTest();
        test.overloadTest();
        test.overReadTest();

    }
    public void overloadTest() throws Exception{
        Class<?> aClass = Class.forName("com.li.jdk.overReadAndoverLoad.bean.Animal");
        Animal animal = (Animal)aClass.newInstance();
        Animal bird = new Bird();
        Animal cat = new Cat();
        // 重载时 方法接受者已经确定为 animal
        animal.print(bird);
        animal.print(cat);

        animal.print(new Bird());
        animal.print(new Cat());

    }

    public void overReadTest() throws Exception{
        Animal animal = new Animal();
        Animal bird = new Bird();
        Animal cat = new Cat();

        animal.run();
        bird.run();
        cat.run();
    }
}
