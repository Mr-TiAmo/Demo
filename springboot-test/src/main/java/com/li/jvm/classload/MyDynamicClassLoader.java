package com.li.jvm.classload;

import java.io.IOException;

/**
 * @program: demo
 * @description:  动态编译一个java文件，然后加载到JVM
 * @author: li
 * @create: 2022-09-01 10:50
 **/
public class MyDynamicClassLoader {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException {
//        //java代码
//        String javaCode = "	package debug_jdk8s;			"+
//                "		public class Book {			"+
//                "			public int i = 10;		"+
//                "		}							";
//        // 编译，加载class
//        Class<?> clazz = CompilerUtils.CACHED_COMPILER.loadFromJava("debug_jdk8s.Book", javaCode);
//        Object a = clazz.newInstance();
//        System.err.println(a);
    }
}
