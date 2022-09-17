package com.li.jdk.date;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-09-13 11:23
 **/
public class DateFormatTest {

    /**
     * 夏令时
     * 1986年5月4日至9月14日
     * 1987年4月12日至9月13日
     * 1988年4月10日至9月11日
     * 1989年4月16日至9月17日
     * 1990年4月15日至9月16日
     * 1991年4月14日至9月15日
     *
     * @throws Exception
     */

    public void test1() throws Exception {
        String patterStr = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(patterStr);

        String birthdayStr = "1988-09-11";
        // 字符串 -> Date -> 字符串
        Date birthday = dateFormat.parse(birthdayStr);
        long birthdayTimestamp = birthday.getTime();
        System.out.println("老王的生日是：" + birthday);
        System.out.println("老王的生日的时间戳是：" + birthdayTimestamp);

        System.out.println("==============程序经过一番周转，我的同时 方法入参传来了生日的时间戳=============");
        // 字符串 -> Date -> 时间戳 -> Date -> 字符串
        birthday = new Date(birthdayTimestamp);
        System.out.println("老王的生日是：" + birthday);
        System.out.println("老王的生日的时间戳是：" + dateFormat.format(birthday));
    }

    public void test2() throws Exception {
        String patterStr = "yyyy-MM-dd HH:mm:ss";
        Date currDate = new Date(System.currentTimeMillis());

        // 北京时区
        DateFormat bjDateFormat = new SimpleDateFormat(patterStr);
        bjDateFormat.setTimeZone(TimeZone.getDefault());
        // 纽约时区
        DateFormat newYorkDateFormat = new SimpleDateFormat(patterStr);
        newYorkDateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        // 伦敦时区
        DateFormat londonDateFormat = new SimpleDateFormat(patterStr);
        londonDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/London"));

        System.out.println("毫秒数:" + currDate.getTime() + ", 北京本地时间:" + bjDateFormat.format(currDate));
        System.out.println("毫秒数:" + currDate.getTime() + ", 纽约本地时间:" + newYorkDateFormat.format(currDate));
        System.out.println("毫秒数:" + currDate.getTime() + ", 伦敦本地时间:" + londonDateFormat.format(currDate));
    }

    public void test10() throws Exception {
        String d = "yyyy-MM-dd HH:mm:ss";
        String time = "1989-07-01 00:00:00";
        SimpleDateFormat format = new SimpleDateFormat(d);
//        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));//615222000000
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));//615225600000
        Date parse = format.parse(time);
        System.out.println(parse.getTime());


        System.out.println(format.format(parse));
    }

    public void test11() throws Exception {
        String d = "yyyy-MM-dd HH:mm:ss";
//        Date date = new Date(615222000000L);
        Date date = new Date(615225600000L);
        SimpleDateFormat format = new SimpleDateFormat(d);
//        format.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        System.out.println(format.format(date));
    }

    public static void main(String[] args) throws Exception {
        DateFormatTest test = new DateFormatTest();
//        test.test1();
        test.test2();
    }
}
