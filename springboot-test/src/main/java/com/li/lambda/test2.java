package com.li.lambda;

import com.alibaba.fastjson.JSONObject;
import com.li.entity.Company;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-05-26 19:38
 **/
public class test2 {
    public static void main(String[] args) {
        List<Company> companies = Arrays.asList(new Company().setCityId(3).setCpName("111").setAddress("北京市"),
                new Company().setCityId(2).setCpName("222"),
                new Company().setCityId(1).setCpName("333"),
                new Company().setCityId(1).setCpName("333"),
                new Company().setCityId(2).setCpName("333"));

        Map<Integer, List<Company>> collect = companies.parallelStream().collect(Collectors.groupingBy(Company::getCityId));
        System.out.println(JSONObject.toJSONString(collect));
        companies.stream().filter(i -> i.getCityId() != 1)
                .peek(i -> System.out.println(i))
                .map(Company::getCityId)
                .peek(i -> System.out.println(i))
                .collect(Collectors.toList());


        System.out.println("》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");

        List<Company> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Company company = new Company();
            company.setCityId(i).setCpName("hello");
            list.add(company);
        }
        System.out.println(list);
        List<Company> collect1 = list.parallelStream().filter(i -> i.getCityId() != 1)
                .peek(i -> System.out.println(Thread.currentThread().getName() + ">>>>>>数据" + i.getCityId()))
                .collect(Collectors.toList());

        Company company;
        List<Company> sum = new ArrayList<>();
        company = new Company();
        company.setCityId(10).setId(20);
        sum.add(company);
        company = new Company();
        company.setCityId(20).setId(50);
        sum.add(company);
        company = new Company();
        company.setCityId(40).setId(60);
        sum.add(company);

        Optional<Integer> reduce = sum.stream().map(i -> i.getId() - i.getCityId()).reduce(Integer::sum);
        System.out.println(reduce);
        System.out.println("》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");



        List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");

        Map<String, Long> result = items.stream()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );
        System.out.println(result);
        System.out.println("》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");

        IntSummaryStatistics collect2 = items.stream()
                .collect(Collectors.summarizingInt(String::length));
        System.out.println(collect2);

        System.out.println("》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》》");

        Map<Integer, Set<String>> collect3 = items.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        System.out.println(collect3);
        Map<Boolean, List<String>> collect4 = items.stream()
                .collect(Collectors.partitioningBy(i -> i.length() > 5));
        System.out.println(collect4);


    }


}
