package com.li.collection;


import com.li.spring.bean.Person;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-05-09 10:11
 **/
public class ListTest {

    public static void main(String[] args) {
        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
        objectObjectConcurrentHashMap.put("1",1);
        objectObjectConcurrentHashMap.put("zz",1);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(null,null);
        List<Person> personList = new ArrayList<>();
        Hashtable<Object, Object> table = new Hashtable<>(6);
        table.put("null","null");
        table.put("null","null");
        HashSet<Object> objects = new HashSet<>();
        objects.add(null);
        objects.add(null);
        LinkedHashSet<Object> objects1 = new LinkedHashSet<>();
        objects1.add(null);


        personList.add(new Person("张三",15));
        personList.add(new Person("张三",16));
        personList.add(new Person("张三",17));
        personList.add(new Person("张三",18));

        List<String> listTest = new ArrayList<>();
        listTest.add("111");
        listTest.add("222");
        listTest.add("333");
        listTest.add("444");
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add(0, "333");
        linkedList.add(1, "222");
        linkedList.add(2, "111");
        test2(listTest,personList);

//        for (int i =0; i<100; i++) {
//
//            new Thread(() -> {
//                System.out.println(Thread.currentThread().getName());
//                test2(arrayList);
//            }).start();
//        }


//        test3(arrayList);

//        test4(listTest);
//        test5(listTest);

        test6(linkedList);
    }

    public static void test1() {
        List<String> strings = Arrays.asList("111", "222", "333");
        /**
         * 报UnsupportedOperationException异常 Arrays.asList() 返回java.util.Arrays$ArrayList， 而不是ArrayList
         * Arrays$ArrayList 和 ArrayList 都继承abstractList；remove、add方法在abstractList中默认 抛UnsupportedOperationException异常
         * 而且不做任何操作， ArrayList 重写了这些方法可以进行 remove、add， 而Arrays$ArrayList没有重写
         * 解决 new ArrayList<>(Arrays$ArrayList);
         */
//        strings.add("444");
        ArrayList<String> arrayList = new ArrayList<>(strings);
    }

    public static void test2(List<String> listTest,List<Person> personList) {
        /**
         * 报ConcurrentModificationException异常；java集合的一种错误检测机制，当多个线程对集合进行结构上的操作是，会产生fail-fast
         * 机制，迭代器在遍历时直接访问集合中的内容，并且在遍历过程中使用一个 modCount 变量。集合在被遍历期间如果内容发生变化，
         * 就会改变modCount的值。每当迭代器使用hashNext()/next()遍历下一个元素之前，都会检测modCount变量是否为expectedmodCount值，
         * 是的话就返回遍历；否则抛出异常，终止遍历
         *
         * 解决办法：
         * 在遍历过程中，所有涉及到改变modCount值得地方全部加上synchronized。
         * 使用CopyOnWriteArrayList来替换ArrayList
         *
         * Iterator 的特点是只能单向遍历，但是更加安全，因为它可以确保，在当前遍历的集合元素被更改的时候，
         * 就会抛出 ConcurrentModificationException 异常
         */
//        for (String s : listTest) {
//            if (s.equals("222")) {
//                listTest.remove(s);
//            }
//        }
//
//        for (Person person : personList) {
//            if (16 == person.getAge()) {
//                personList.remove(person);
//            }
//        }

        Iterator<String> iterator = listTest.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.equals("222")) {
//                listTest.remove(next);
                iterator.remove();
            }
        }
        System.out.println(listTest);
    }

    public static void test3(List<String> listTest) {
        /**
         * 不可修改的list
         */
        Collection<String> strings = Collections.unmodifiableCollection(listTest);
        strings.add("444");
    }

    public static void test4(List<String> listTest) {
        /**
         * Iterator 和 ListIterator 区别
         * Iterator 可以遍历 set 和 list ListIterator只能遍历 list
         * Iterator 只能单向顺序遍历， ListIterator可以双向遍历
         * ListIterator 实现 Iterator 接口，然后添加了一些额外的功能，比如添加一个元素(add)、替换一个元素(set)、获取前面或后面元素的索引位置
         */
        ListIterator<String> listIterator = listTest.listIterator();
        while (listIterator.hasNext()) {
            String next = listIterator.next();
            System.out.println(next);
            System.out.println(listIterator.nextIndex());
            if (next.equals("222")) {
                listIterator.set("444"); //替换元素
            }
        }
        System.out.println("》》》》》》》》》》》》》》》》》》》》》》");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
            System.out.println(listIterator.previousIndex());

        }

    }

    public static void test5(List<String> listTest) {
        /**
         * list 的遍历方式
         * ArrayList用for循环遍历比iterator迭代器遍历快，LinkedList用iterator迭代器遍历比for循环遍历快
         */
        for (String s : listTest) {
            System.out.println(s);
        }

        listTest.forEach(i -> {
            System.out.println(i);
        });

        Iterator<String> iterator = listTest.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    public static void test6(LinkedList<String> linkedList) {
        System.out.println("》》》》》》》》》》》》》》》》》》》》》》get");
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());

        System.out.println("》》》》》》》》》》》》》》》》》》》》》》add");
        linkedList.addFirst("444");
        linkedList.addLast("000");

        System.out.println("》》》》》》》》》》》》》》》》》》》》》》iterator遍历");
        Iterator<String> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("》》》》》》》》》》》》》》》》》》》》》》listIterator遍历");
        ListIterator<String> stringListIterator = linkedList.listIterator(1);
        while (stringListIterator.hasPrevious()) {
            System.out.println(stringListIterator.previous());
        }

    }



    /**
     * 模拟 栈
     */
    class StackL {
        private LinkedList<String> linkedList;

        public void push(String s) {
            linkedList.addFirst(s);
        }
        public void top(String s) {
            linkedList.addLast(s);
        }
        public void pop() {
            linkedList.removeFirst();
        }
    }
}
