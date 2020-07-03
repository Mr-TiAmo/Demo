package com.li.scheduled;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.ScheduledLockConfiguration;
import net.javacrumbs.shedlock.spring.ScheduledLockConfigurationBuilder;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.time.Duration;

/**
 * @program: Demo
 * @description:  defaultLockAtMostFor = "PT30S" 指定执行节点结束时 锁的保留时间 这里默认10s
 * @author: 栗翱
 * @create: 2020-07-03 14:14
 **/

@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT10S")
public class ShedLockRedisConfig {

    /**
     * 是一个在分布式环境中使用的定时任务框架，用于解决在分布式环境中的多个实例的相同定时任务在同一时间点重复执行的问题，
     * 解决思路是通过对公用的数据库中的某个表进行记录和加锁，使得同一时间点只有第一个执行定时任务并成功在数据库表中写入
     * 相应记录的节点能够成功执行而其他节点直接跳过该任务。简单来说，ShedLock本身只做一件事情：保证一个任务最多同时执行一次。
     * 所以如官网所说的，ShedLock不是一个分布式调度器，只是一个锁!
     */



    @Bean
    public LockProvider lockProvider(DataSource dataSource) {
        return new JdbcTemplateLockProvider(dataSource);
    }

    @Bean
    public ScheduledLockConfiguration scheduledLockConfiguration(LockProvider lockProvider) {
        return ScheduledLockConfigurationBuilder
                .withLockProvider(lockProvider)
                .withPoolSize(10)
                .withDefaultLockAtMostFor(Duration.ofMinutes(10))
                .build();
    }

}
