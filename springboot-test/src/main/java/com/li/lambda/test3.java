package com.li.lambda;

import com.li.comm.utils.DateUtils;
import com.li.entity.Company;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @program: demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-18 16:22
 **/
@Configuration
public class test3 {

    public static void main(String[] args) {
        Company company;
        List<Company> sum = new ArrayList<>();
        company = new Company();
        company.setCityId(10).setId(20).setHaveUpdate(20).setCreateTime(LocalDate.now().minusDays(1));
        sum.add(company);
        company = new Company();
        company.setCityId(20).setId(50).setHaveUpdate(30).setCreateTime(LocalDate.now().minusDays(1));
        sum.add(company);
        company = new Company();
        company.setCityId(40).setId(60).setHaveUpdate(10).setCreateTime(LocalDate.now().minusDays(2));
        sum.add(company);
        company = new Company();
        company.setCityId(10).setId(60).setHaveUpdate(50).setCreateTime(LocalDate.now().minusDays(2));
        sum.add(company);

        company = new Company();
        company.setCityId(20).setId(50).setHaveUpdate(20).setCreateTime(LocalDate.now().minusDays(10));
        sum.add(company);


        company = new Company();
        company.setCityId(20).setId(50).setHaveUpdate(20).setCreateTime(LocalDate.now().withMonth(12));
        sum.add(company);

        company = new Company();
        company.setCityId(20).setId(50).setHaveUpdate(20).setCreateTime(LocalDate.now().withMonth(11));
        sum.add(company);

        company = new Company();
        company.setCityId(20).setId(50).setHaveUpdate(20).setCreateTime(LocalDate.now().withMonth(1));
        sum.add(company);

        Map<Integer, IntSummaryStatistics> collect = sum.parallelStream().collect(Collectors.groupingBy(Company::getCityId, Collectors.summarizingInt(Company::getId)));
        System.out.println(collect);

        //stream 多个对象 分组 返回一个对象并求和
        Map<Integer, Company> collect1 = sum.parallelStream().collect(Collectors.toMap(Company::getCityId, Function.identity(), (u1, u2) -> {
            u1.setId(u1.getId() + u2.getId());
            u1.setHaveUpdate(u1.getHaveUpdate() + u2.getHaveUpdate());
            return u1;
        }));
        System.out.println(collect1);

        // 按 每周分组
        Map<LocalDate, List<Company>> collect2 = sum.parallelStream()
                .collect(Collectors.groupingBy(item -> item.getCreateTime().with(TemporalAdjusters.previousOrSame(DayOfWeek.of(1)))));
        System.out.println(collect2);

        // 按 每月分组
        Map<LocalDate, List<Company>> collect3 = sum.parallelStream()
                .collect(Collectors.groupingBy(item -> item.getCreateTime().with(TemporalAdjusters.lastDayOfMonth())));
        System.out.println(collect3);


        //多属性分组
        Map<String, List<Company>> collect4 = sum.parallelStream()
                .collect(Collectors.groupingBy(item -> getKey(item)));
        System.out.println(collect4);


        ///
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        List<Company> companies = Arrays.asList(new Company().setCityId(3).setCpName("111").setAddress("北京市"),
                new Company().setCityId(2).setCpName("222"),
                new Company().setCityId(1).setCpName("333"),
                new Company().setCityId(1).setCpName("333"),
                new Company().setCityId(2).setCpName("333"));
        Map<Integer, List<Company>> collect5 = companies.parallelStream().collect(Collectors.groupingBy(Company::getCityId));
//        for (Integer integer : collect5.keySet()) {
//            System.out.println(integer+">>>>>>>>>>>>>>" +collect5.get(integer));
//        }
        Iterator<Integer> iterator = collect5.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = iterator.next();
            System.out.println(key+">>>>>>>>>>>>>>" +collect5.get(key));
        }
        System.out.println(collect5);
    }

    public static String getKey(Company company) {
        return  company.getId() +"#"+ company.getCityId();
    }
}
