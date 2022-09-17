package com.li.jvm.classload;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-09-01 09:53
 **/
public class ClassLoadTest extends ClassLoader {
    private String url;


    public ClassLoadTest() {
    }

    public ClassLoadTest(String url) {
        this.url = url;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    public static void main(String[] args) {

    }
}
