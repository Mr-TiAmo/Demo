package com.li.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: dubbo-demo
 * @description:
 * @author: li
 * @create: 2022-08-29 13:45
 **/
public class CollectionTest {
    public static void main(String[] args) {
//        CopyOnWriteArrayList<Object> copyOnWriteArray = new CopyOnWriteArrayList<>();
//        copyOnWriteArray.add();
//        copyOnWriteArray.set();
//        copyOnWriteArray.get();
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
//        for (Object o : arrayList) {
//            arrayList.remove(o);
//        }
        Iterator<Object> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            iterator.remove();
        }

        System.out.println(arrayList);

//        LinkedList<Object> objects = new LinkedList<>();
//        objects.add();
//        objects.set();
//        objects.get();
//        objects.remove();
//
//        CopyOnWriteArrayList<Object> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
//        copyOnWriteArrayList.add();
//
////        HashSet<Object> hashSet = new HashSet<>();
////        hashSet.add();
////        hashSet.remove();
////        LinkedHashSet<Object> linkedHashSet = new LinkedHashSet<>();
////        linkedHashSet.add();
////        linkedHashSet.remove();
////        TreeSet<Object> treeSet = new TreeSet<>();
////        treeSet.add();
////        treeSet.remove();
//
        ConcurrentHashMap<Object, Object> hashMap = new ConcurrentHashMap<>(3);
        hashMap.put("1",1);
//        hashMap.put()
//        hashMap.get()
//        hashMap.remove()
//        HashMap<Object> hashMap = new HashMap<>();
//        hashMap.put();
//        hashMap.remove();
//        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
//        linkedHashMap.put();
//        linkedHashMap.remove();
//        TreeMap<Object, Object> treeMap = new TreeMap<>();
//        treeMap.put();
//        treeMap.remove();
    }


    /**
     *
     * 根据 RandomAccess 的注释理解，如果“代码一”的执行速度比“代码二”快，则应该实现 RandomAccess 接口。换句话说，如果集合类（List）实现了 RandomAccess 接口，则采用“代码一”的方式能够获得更高的执行效率。这也是为什么阿里巴巴的代码规约中推荐使用“代码一”的方式进行集合遍历。
     *
     *1. 被 List 实现类使用的标记接口，标识该类支持快速随机访问（通常是常量访问时间）。该接口的主要目的是在进行随机或者串行访问 list 时，允许算法修改他们的行为来获得更好的性能。
     *
     * 2. 将随机访问 lists（如 ArrayList）的最好算法应用于顺序访问 lists（如 LinkedList）时，可能会产生二次行为（我的理解：这里的二次行为指的是通过遍历 LinkedList ，找到目标对象，
     *这比通过 ArrayList 银锁直接获取目标对象，多了一个遍历行为，这个遍历行为就是二次行为）。如果将随机访问算法应用于串行访问 list 时，将会产生较差的性能。鼓励通用列表算法在应用之前，
     *先检查 List 是否实现了 RandomAccess 接口，如果实现该接口，则采用快速随机访问法进行遍历，否则使用迭代器的方式进行访问。
     * 3. 人们认识到随访访问和串行访问的区别通过是模糊的。例如，如果 List 变得很大，则提供渐近线性的访问时间，然而在实践中基本上是常量访问时间，像这样的 List 应该实现RandomAccess 接口。根据经验，如果“算法一”的访问速度比“算法二”快，则 List 应该实现RandomAccess 接口。
     * algorithm1:
     * <pre>
     *     for (int i=0, n=list.size(); i &lt; n; i++)
     *         list.get(i);
     * </pre>
     * runs faster than this loop:
     * algorithm2:
     * <pre>
     *     for (Iterator i=list.iterator(); i.hasNext(); )
     *         i.next();
     * </pre>
     * <p>This interface is a member of the
     * <a href="{@docRoot}/../technotes/guides/collections/index.html">
     * Java Collections Framework</a>.
     *
     * @since 1.4
     */










    /**
     * 1 系统信息， 包含 已运行多久，几个用户， 当前系统时间，每个cpu负载信息
     * 2 任务进程列表 一共多少个进程， 几个在运行，几个挂起，几个停止，几个僵尸进程
     * 3 cpu状态信息， 用户空间占用cpu百分比，内核空间cpu占用百分比 ，空闲cpu百分比
     * 4 内存空间 ，总内存大小， 已使用 ， 剩余，
     * 5 内存swap交换分区 信息， 总大小， 已使用， 剩余，
     *
     * 进程id 所属用户 cpu使用率 内存使用率 当前进程状态
     *
     * 命令
     * top -H 设置线程模式
     * top -d 设置刷新时间
     * top -n 设置退出的刷新次数
     * top -H -d -n -p
     *
     *
     * jps 显示正在运行的java虚拟机进程信息，包含进程主类main函数所在类的名称，本地虚拟机唯一id
     * jps -q 只显示进程id
     * jps -m：显示java虚拟机启动时传递给main函数的参数信息
     * jps -l 显示主类的全限定名称
     * jps-v 显示jvm启动传递的参数信息
     *
     * jinfo：显示正在运行的java应用进程的参数配置信息 ，包含java system信息和jvm启动参数行信息，同时动态的修改一些jvm配置信息
     *
     * jinfo -flags pid：输出所有参数信息
     * jinfo -flag name pid：输出执行参数信息
     * jinfo -flag +/- name pid：开启或关闭指定参数配置
     * jinfo -sysprops ：输出所有系统属性
     *
     *
     * jstat：jvm监测统计工具，包含内存和gc信息
     *
     * jstat -class -p：类加载统计信息，加载类数量、大小，卸载类数量、大小， 消耗时间
     * jstat -gcutil -p
     * jstat -gc -p
     * jstat -newcapitacal
     *
     *
     * jmap -dump:format=b,file=xxx.dump -p   需要开启 -XX:heapDumpOnOutOfMemoryError -XX:heapDumPath
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */

}
