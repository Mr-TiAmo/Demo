package com.li.jvm.classload;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * @program: demo
 * @description:  加载一个url地址上的类
 * @author: li
 * @create: 2022-09-01 10:38
 **/
public class MyURLClassLoader extends ClassLoader {
    private String url;

    public MyURLClassLoader(String url) {
        this.url = url;
    }

    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            String path = url + "/" + name.replace(".", "/") + ".class";
            URL url = new URL(path);
            InputStream inputStream = url.openStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = -1;
            byte buf[] = new byte[1024];
            while ((len = inputStream.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            byte[] data = baos.toByteArray();
            inputStream.close();
            baos.close();
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        MyURLClassLoader classLoader = new MyURLClassLoader("http://localhost:8080/examples");
        Class clazz = classLoader.loadClass("com.hadluo.Demo");
        Object obj = clazz.newInstance();
    }

}
