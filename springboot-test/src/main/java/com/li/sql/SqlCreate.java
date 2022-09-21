package com.li.sql;

import com.li.util.RandomUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-09-16 11:35
 **/
public class SqlCreate {

    public static void main(String[] args) throws Exception {
        final String url = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=UTC";
        final String name = "com.mysql.cj.jdbc.Driver";
        final String user = "root";
        final String password = "123456";
        Class<?> aClass = Class.forName(name);
        Connection connection = DriverManager.getConnection(url, user, password);
        if (connection != null) {
            insert(connection, 50000);
        }
    }

    /**
     * 设置max_allowed_packet
     * set global max_allowed_packet = 100*1024*1024*
     * set global max_allowed_packet = 104857600
     *
     * @param conn
     * @throws Exception
     */
    public static void insert(Connection conn, int num) throws Exception {

        // 开始时间
        Long begin = System.currentTimeMillis();
        String prefix = " INSERT INTO `vote_record` VALUES";
        StringBuilder suffix;

        conn.setAutoCommit(false);
        PreparedStatement preparedStatement = conn.prepareStatement(" ");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 1; i <= 100; i++) {
            suffix = new StringBuilder();
            for (int j = 0; j <= num; j++) {
                int user_id = i*j;
                int vote_num = cn.hutool.core.util.RandomUtil.randomInt(1, 100);
                int group_id = cn.hutool.core.util.RandomUtil.randomInt(1, 10);
                int status = cn.hutool.core.util.RandomUtil.randomInt(1, 5);
                String date = "'" + format.format(new Date()) + "'";
                suffix.append(" (")
                        .append("null,")
                        .append(user_id + ",")
                        .append("'" + RandomUtil.generateString(50) + "',")
                        .append(vote_num + ",")
                        .append(group_id + ",")
                        .append(status + ",")
                        .append(date + ",")
                        .append(date)
                        .append("),");
            }
            String sql = prefix + suffix.substring(0, suffix.length() - 1);
            preparedStatement.addBatch(sql);
            preparedStatement.executeBatch();
            conn.commit();

            // 清空上一次添加的数据
            suffix = new StringBuilder();
            System.out.println("--------" + i + "%--------");
        }

        preparedStatement.close();
        conn.close();
        Long end = System.currentTimeMillis();
        // 耗时
        System.out.println("1000万条数据插入花费时间 : " + (end - begin) / 1000 + " s");
    }
}
