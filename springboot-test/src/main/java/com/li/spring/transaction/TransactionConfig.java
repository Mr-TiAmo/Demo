package com.li.spring.transaction;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2022-07-31 09:10
 **/
@Configuration
@ComponentScan("com.li.spring.transaction")
@EnableTransactionManagement
public class TransactionConfig {


    @Bean
    public DataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("123");
        dataSource.setURL("jdbc:mysql://192.168.1.188:3306/client_test?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull");
        dataSource.setDatabaseName("test");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

    @Bean
    public PlatformTransactionManager getTransactionManager() {
        return new DataSourceTransactionManager(getDataSource());
    }
}
