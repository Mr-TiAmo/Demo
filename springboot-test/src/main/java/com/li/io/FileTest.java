package com.li.io;

import com.li.entity.Order;

import java.io.*;


/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-01-06 10:17
 **/
public class FileTest {

    public static void test1() throws Exception {
        File file1 = new File("C:\\test\\123.txt");

        String parent = "C:\\test";
        String child = "123.txt";
        File file2 = new File(parent, child);

        File fileParent = new File("C:\\test");
        File file3 = new File(fileParent, child);

        System.out.println("文件的绝对路径"+ file1.getAbsolutePath());
        System.out.println("文件的构造路径"+ file1.getPath());
        System.out.println("文件的名称"+ file1.getName());
        System.out.println("文件的长度"+ file1.length());
        //文件的绝对路径C:\test\123.txt
        //文件的相对路径C:\test\123.txt
        //文件的名称123.txt
        //文件的长度0
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");

        //文件遍历

        File dirs = new File("C:\\test");
        //只获得当前目录下的文件夹及文件的名称，不包含子目录
        String[] names = dirs.list();
        for (String name : names){
            System.out.println(name);
        }
        //获取当前面目录下的文件夹及文件的对象
        File[] files = dirs.listFiles();
        for (File file : files){
            System.out.println(file);
            System.out.println(file.getName());
        }
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");

        getDirection(dirs);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
        OutputStream outputStream = new FileOutputStream(new File("C:\\test\\hello.txt"), true);
//        outputStream.write(65);
        byte[] bytes = new String("abcdefg").getBytes();
        outputStream.write(bytes);
        //换行
//        outputStream.write("\r\n".getBytes());
        outputStream.close();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
        FileInputStream inputStream = new FileInputStream(new File("C:\\test\\hello.txt"));
        int flag;
        byte[] data = new byte[3];
        while ((flag = inputStream.read(data)) != -1){
            System.out.println(new String(data, 0, flag));
        }
        inputStream.close();


        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
        File ImageFile = new File("C:\\Users\\EDZ\\Desktop\\06a1e703ec6479ace979905b4fc3af0d19d5f6cf8c117a7e323e0e0771bcbf32.jpg");
        getImage(ImageFile, "C:\\test\\06a1e703ec6479ace979905b4fc3af0d19d5f6cf8c117a7e323e0e0771bcbf32.jpg");


        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
        File helloFile = new File("c:\\test\\hello.txt");
        FileReader fileReader = new FileReader(new File("c:\\test\\hello.txt"));
        FileWriter fileWriter = new FileWriter(new File("c:\\test\\hello1.txt"));
        int len;
        char[] chars = new char[1024];
        while((len = fileReader.read(chars)) != -1){
            fileWriter.write(chars, 0, len);
        }
        fileWriter.flush();
        fileWriter.close();
        fileReader.close();

    }


    /**
     * 缓冲流
     * @throws Exception
     */
    public static void test2() throws Exception{
        File file = new File("C:\\Users\\EDZ\\Desktop\\parking_open_api.zip");
        File filePath = new File("C:\\test\\parking_open_api.zip");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);

        //缓冲流
        BufferedInputStream inputStream = new BufferedInputStream(fileInputStream);
        BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream);
        long start = System.currentTimeMillis();
        int len;
        while((len = inputStream.read()) != -1){
            outputStream.write(len);
        }
        fileInputStream.close();
        inputStream.close();
        outputStream.flush();
        fileOutputStream.close();
        outputStream.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start +"毫秒");
        //3647毫秒
    }

    /**
     * 缓冲流
     * @throws Exception
     */
    public static void test3() throws Exception{
        File file = new File("D:\\tools\\Linux\\CentOS-7-x86_64-DVD-1908.iso");
        File filePath = new File("C:\\test\\CentOS-7-x86_64-DVD-1908.iso");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);

        BufferedInputStream inputStream = new BufferedInputStream(fileInputStream);
        BufferedOutputStream outputStream = new BufferedOutputStream(fileOutputStream);
        long start = System.currentTimeMillis();
        int len;
        //读取大文件1024速度比较快，数组快
        byte[] data = new byte[1024] ;
        while((len = inputStream.read(data)) != -1){
            outputStream.write(data, 0, len);
        }
        fileInputStream.close();
        inputStream.close();
        outputStream.flush();
        fileOutputStream.close();
        outputStream.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start +"毫秒");
        //230毫秒
    }

    /**
     * 字节转换流
     * @throws Exception
     */
    public static void test4() throws Exception{
        File filePath = new File("C:\\test\\java.txt");
        //这种情况可能会乱码
//        FileReader fileInputStream = new FileReader(filePath);
        //InputStreamReader 字节转换流
        InputStreamReader fileInputStream = new InputStreamReader(new FileInputStream(filePath), "gbk");
        BufferedReader inputStream = new BufferedReader(fileInputStream);
        String len;
        while((len = inputStream.readLine()) != null){
            //字符缓冲流，读取一行
            System.out.println(len);
        }
//        int len;
//        char[] chars = new char[1024];
//        while((len = inputStream.read(chars)) != -1){
//            System.out.println(new String(chars, 0, len));
//        }
        inputStream.close();
        fileInputStream.close();
    }

    /**
     * 序列化流
     * @throws Exception
     */
    public static void test5() throws Exception{
        File file = new File("c:\\test\\AccessCard.txt");

        //将对象序列化
        Order order = new Order();
        order.setId(1);
        order.setName("张三");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(order);
        objectOutputStream.flush();
        objectOutputStream.close();

        //将对象反序列化
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        Object readObject = objectInputStream.readObject();
        System.out.println(">>>>>>>>>"+(Order)readObject);
        objectInputStream.close();
    }

    public static void main(String[] args) throws Exception{
        test5();
    }
    /**
     * 打印指定目录下及其子目录中的文件名
     * @param file
     */
    public static void getDirection(File file){
        //是目录才开始遍历
        if (!file.isDirectory()){
            return ;
        }
        File[] files = file.listFiles();
        for (File f : files){
            if (f.isDirectory()){
                //是目录继续递归
                getDirection(f);
            }else {
                //不是目录打印名称
                System.out.println(f.getName());
            }
        }
    }

    /**
     * 复制图片
     */
    public static void getImage(File file, String path) throws Exception{
        int x;
        byte[] bytes = new byte[1024];

        FileOutputStream outputStream = new FileOutputStream(new File(path));
        FileInputStream inputStream = new FileInputStream(file);
        while ((x = inputStream.read(bytes)) != -1){
            outputStream.write(bytes,0, x);
        }
        outputStream.close();
        inputStream.close();

    }

}
