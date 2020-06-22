package com.li.lambda;

import com.li.entity.Company;
import com.li.entity.ParkingDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: parking_open_api_12.26
 * @description: https://blog.csdn.net/qq_37176126/article/details/81273195
 * @author: 栗翱
 * @create: 2020-01-18 13:28
 **/
public class test1 {
    public static void main(String[] args) throws Throwable {
        List<Company> companies = Arrays.asList(new Company().setCityId(3).setCpName("111").setAddress("北京市"), new Company().setCityId(2).setCpName("222"),
                new Company().setCityId(1).setCpName("333"));

        List<Company> collect2 = companies.stream().filter(i -> i.getCityId() > 1).collect(Collectors.toList());
        companies.forEach(i -> System.out.println(i.getCpName()));

        System.out.println("》》》》》》》》》》》》》》》");
        companies.stream().filter(i -> i.getCityId() > 1)
                .forEach(i -> System.out.println(i.getCityId()));

        System.out.println("》》》》》》》》》》》》》》》");
        companies.stream().filter(i -> i.getCityId() > 1)
                .filter(i -> i.getCityId() < 3)
                .forEach(i -> System.out.println(i.getCityId()));

        System.out.println("》》》》》》》and or 过滤器》》》》》》》》");
        Predicate<Company> firstFilter = i -> i.getCityId() > 1;
        Predicate<Company> secondFilter = i -> i.getCpName().equals("333");
        companies.stream().filter(firstFilter.or(secondFilter))
                .forEach(i -> System.out.println(i.getCityId()));

        System.out.println("》》》》》》》》》输出条数限制》》》》》》");
        companies.stream().limit(2).forEach(i -> System.out.println(i));

        System.out.println("》》》》》》》》整数排序》》》》》》》");
        companies.stream().sorted((s1, s2) -> s1.getCityId() - s2.getCityId())
                .forEach(i -> System.out.println(i));

        System.out.println("》》》》》》》》》字符串排序》》》》》》");
        companies.stream().sorted(Comparator.comparing(Company::getCpName))
                .forEach(i -> System.out.println(i));

        System.out.println("》》》》》》》》》非空判断》》》》》》");
        companies.stream().map(i -> Objects.nonNull(i.getAddress()) ? i : null)
                .forEach(i -> System.out.println(i));

        System.out.println("》》》》》》》》》非空判断》》》》》》");
        companies.stream().map(i -> Objects.nonNull(i.getAddress()) ? i.getCityId() + 1 : i.getCityId() - 1)
                .forEach(i -> System.out.println(i));

        System.out.println("》》》》》》》》》非空判断》》》》》》");
        //第一个参数是上次函数执行的返回值（也称为中间结果），第二个参数是stream中的元素，
        Integer n = companies.stream().map(i -> Objects.nonNull(i.getCityId()) ? i.getCityId() : 0)
                .reduce((x, y) -> x + y).orElse(0);
        System.out.println(n);
        companies.forEach(i -> System.out.println(i));

        System.out.println("》》》》》》》》》max min 》》》》》》");
        Company company = companies.stream().max(Comparator.comparing(Company::getCpName)).get();
        System.out.println(company);

        System.out.println("》》》》》》》》》collection 》》》》》》");
//        List<Company> collect = companies.stream().map(i -> Objects.nonNull(i.getCityId()) ? i : null).collect(Collectors.toList());
//        List<Company> collect = companies.stream().filter(i -> i.getCityId() > 2).collect(Collectors.toList());
        String collect = companies.stream().map(i -> i.getCpName()).collect(Collectors.joining(","));
        System.out.println(collect);


        System.out.println("》》》》》》》》》build 》》》》》》");
        Company c = Company.builder().cityId(100).cpName("10086").build();
        System.out.println(c);

        List<String> list = Arrays.asList("asd", "dsg");
        String str = list.stream().filter(a -> !a.isEmpty()).collect(Collectors.joining(","));
        System.out.println(str);
        System.out.println(list);


        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        List<ParkingDetails> collect1 = list1.stream().map(i -> {
            ParkingDetails details = new ParkingDetails();
            details.setId(i);
            return details;
        }).collect(Collectors.toList());
        collect1.forEach(System.out::println);

        Stream<String> stringStream = Stream.of("张三", "李四", "王二", "张四五");
        List<Integer> aa = stringStream.filter(x -> x.startsWith("张")).sorted(Comparator.reverseOrder()).map(x -> x.length()).collect(Collectors.toList());
        aa.forEach(System.out::println);


        System.out.println("》》》》》》》》》flatMap 》》》》》》");

        String str1 = "hello world";
        List<Character> arrayList = new ArrayList<>();
        for (char c1 : str1.toCharArray()) {
            arrayList.add(c1);
        }



        System.out.println("》》》》》》》》》Optional 》》》》》》");
        Integer sum = Stream.of(1, 2, 3, 4, 5).reduce(0, Integer::sum);
        System.out.println(sum);

        Optional<Integer> sum1 = Stream.of(1, 2, null, 4, 5).filter(i -> i != null).reduce(Integer::sum);
        System.out.println(sum1);
        if (sum1.isPresent()) {  //不为null 输出
            System.out.println(sum1.get());
            System.out.println(sum1.orElse(0));
            System.out.println(sum1.orElseThrow(() -> new Throwable("不能为null")));
        }
        sum1.ifPresent(i -> System.out.println(i));//不为null 输出

        Optional<Integer> integer = Optional.of(1);  // 不能为null值
        Optional<Object> optionalO = Optional.ofNullable(null);  // 可以为null

        System.out.println(sum1.filter(i -> i > 15));

        String testS[]={"hello"," ","world"," ","!"};
        Optional<String> concat = Stream.of(testS).reduce(String::concat);
        System.out.println(concat.get());
//        System.out.println(concat.map(null));
        System.out.println(concat.map(x -> null));
        System.out.println(concat.map(x -> x.toUpperCase()).get());



    }
}
