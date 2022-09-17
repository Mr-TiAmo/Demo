package com.li.jvm.classload;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @program: demo
 * @description: 自定义Classloader实现不同版本的jar共存
 * @author: li
 * @create: 2022-09-01 10:45
 **/
public class MyVersionClassLoader extends ClassLoader{

    public static void main(String[] args) throws Exception {
        invokeJar("C:/Users/皮吉/Desktop/dobbo-v1.jar");
        invokeJar("C:/Users/皮吉/Desktop/dobbo-v2.jar");

    }

    static class MyClassLoader extends ClassLoader {
        private String jarPath;

        public MyClassLoader(String jarPath) {
            this.jarPath = jarPath;
        }

        @Override
        public Class<?> loadClass(String className) throws ClassNotFoundException {
            byte[] b;
            try {
                // 只对我们的Dubbo类进行 class构造
                if (className.startsWith("classloader.Dubbo")) {
                    b = findJar(className);
                    return defineClass(className, b, 0, b.length);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return super.loadClass(className);
        }

        // 从jar中找到指定class转换成字节流
        private byte[] findJar(String className) throws IOException {
            String tmp = className.replaceAll("\\.", "/");
            @SuppressWarnings("resource")
            JarFile jar = new JarFile(jarPath);
            JarEntry entry = jar.getJarEntry(tmp + ".class");
            InputStream is = jar.getInputStream(entry);
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            int nextValue = is.read();
            while (-1 != nextValue) {
                byteStream.write(nextValue);
                nextValue = is.read();
            }
            return byteStream.toByteArray();
        }

    }

    private static void invokeJar(String jar) throws Exception {
        MyClassLoader v = new MyClassLoader(jar);
        Class<?> clazz = v.loadClass("classloader.Dubbo");
        Object object = clazz.newInstance();
        Method method = clazz.getMethod("invoke", null);
        method.invoke(object, null);
    }
}
