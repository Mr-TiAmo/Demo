package com.li.scheduled;

import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2020-07-03 13:15
 **/
@Component
@EnableScheduling
public class TestSchedule implements ApplicationListener<WebServerInitializedEvent> {
    private Integer port;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void onApplicationEvent(WebServerInitializedEvent webServerInitializedEvent) {
        this.port = webServerInitializedEvent.getWebServer().getPort();
    }

    /**
     * 多个 服务启动时 同一时间只有一个定时器运行
     */
    @Scheduled(cron = "0/10 * * * * ? ")
    public void test() {
        //设置超时 防止抢到任务的服务器宕机
        Boolean lock = false;
        try {
            lock = redisTemplate.opsForValue().setIfAbsent("lock", 1, 5, TimeUnit.SECONDS);
            if (lock) {
                System.out.println(port + "执行了定时任务");
            }
        } finally {
            if (lock) {
                redisTemplate.delete("lock");
                System.out.println("cancelCouponCode任务结束，释放锁!");
            } else {
                System.out.println("cancelCouponCode没有获取到锁，无需释放锁!");
            }

        }
    }


    /**
     * 用于测试 scheduleLock 任务
     *
     * @SchedulerLock()参数： name：用来标注一个定时服务的名字，被用于写入数据库作为区分不同服务的标识，如果有多个同名定时任务则同一时间点只有一个执行成功
     * lockAtMostFor：成功执行任务的节点所能拥有独占锁的最长时间，单位是毫秒ms
     * lockAtMostForString：成功执行任务的节点所能拥有的独占锁的最长时间的字符串表达，例如“PT14M”表示为14分钟
     * lockAtLeastFor：成功执行任务的节点所能拥有独占所的最短时间，单位是毫秒ms
     * lockAtLeastForString：成功执行任务的节点所能拥有的独占锁的最短时间的字符串表达，例如“PT14M”表示为14分钟
     * <p>
     * 任务执行完，会自动释放锁。
     */
    @Scheduled(cron = "0/30 * * * * ? ")
    @SchedulerLock(name = "TestSchedule", lockAtLeastFor = 1 * 1000)
    public void testScheduleLock() {
        System.out.println(port + "执行了scheduleLock定时任务");

    }
}
