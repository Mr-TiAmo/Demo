package com.li.comm.aop;

import com.li.comm.config.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @program: parking_client
 * @description:
 * @author: 栗翱
 * @create: 2020-04-08 14:07
 **/
@Slf4j
@Aspect
@Order(1)
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(com.li.comm.aop.DataSource)")
    public void dsPointCut() {

    }
//    @Around("dsPointCut()")
//    public Object around(ProceedingJoinPoint point) throws Throwable {
//        log.info("aop执行切换数据源！！！");
//        MethodSignature signature = (MethodSignature) point.getSignature();
//        Method method = signature.getMethod();
//        DataSource dataSource = method.getAnnotation(DataSource.class);
//        if (dataSource != null) {
//            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
//        }
//        try {
//            return point.proceed();
//        } finally {
//            // 销毁数据源 在执行方法之后
//            DynamicDataSourceContextHolder.clearDataSourceType();
//        }
//    }

    @Before(value = "dsPointCut()")
    public void beforeDataSource(JoinPoint joinPoint){
        log.info("进入切点>>>>>>>>>>>>>>>>>>>>>>>>>>>执行切换数据源");
        log.info("aop执行切换数据源！！！");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DataSource dataSource = method.getAnnotation(DataSource.class);
        if (dataSource != null) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
        }
    }

    @After("dsPointCut()")
    public void afterDataSource(){
        DynamicDataSourceContextHolder.clearDataSourceType();
    }
}
