package com.li.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: parking_open_api_12.26
 * @description:
 * @author: 栗翱
 * @create: 2020-02-13 09:36
 **/
public class Time {

    public static void main(String[] args) throws ParseException {


        // 字符串 转 LocalDateTime
        String str = "1986-04-12 12:30:01";
        DateTimeFormatter time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(LocalDateTime.parse(str, time));

        // 格式化输出 当前时间戳
        System.out.println(time.format(LocalDateTime.now()));

        // 获取秒数
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).getEpochSecond());
        // 获取毫秒
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());


        // 字符串 转 date
        DateTimeFormatter day = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(str, day);
        Date date = Date.from(parse.toInstant(ZoneOffset.of("+8")));
        System.out.println(date);

        // date  转 字符串
        Date date1 = new Date();
        LocalDateTime localDateTime = date1.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        System.out.println(day.format(localDateTime));

        // 字符串 转 时间戳
        LocalDateTime parse1 = LocalDateTime.parse(str, time);
        System.out.println(parse1.toInstant(ZoneOffset.of("+8")).toEpochMilli());

        // 时间戳 格式化输出 字符串
        LocalDateTime localDateTime1 = new Date(1635489604917L).toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        System.out.println(day.format(localDateTime1));

    }

    public static String getTime(Date currentTime, Date firstTime) {
        long diff = currentTime.getTime() - firstTime.getTime();//这样得到的差值是微秒级别
        Calendar currentTimes = dataToCalendar(currentTime);//当前系统时间转Calendar类型
        Calendar firstTimes = dataToCalendar(firstTime);//查询的数据时间转Calendar类型
        int year = currentTimes.get(Calendar.YEAR) - firstTimes.get(Calendar.YEAR);//获取年
        int month = currentTimes.get(Calendar.MONTH) - firstTimes.get(Calendar.MONTH);
        int day = currentTimes.get(Calendar.DAY_OF_MONTH) - firstTimes.get(Calendar.DAY_OF_MONTH);
        if (day < 0) {
            month -= 1;
            currentTimes.add(Calendar.MONTH, -1);
            day = day + currentTimes.getActualMaximum(Calendar.DAY_OF_MONTH);//获取日
        }
        if (month < 0) {
            month = (month + 12) % 12;//获取月
            year--;
        }
        long days = diff / (1000 * 60 * 60 * 24);
        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60); //获取时 
        long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);//获取分钟
        long s = (diff / 1000 - days * 24 * 60 * 60 - hours * 60 * 60 - minutes * 60);//获取秒
        String CountTime = "" + year + "年" + month + "月" + day + "天" + hours + "小时" + minutes + "分" + s + "秒";
        return CountTime;
    }
    //Date类型转Calendar类型
    public static Calendar dataToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }



}
