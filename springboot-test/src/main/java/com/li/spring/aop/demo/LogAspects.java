package com.li.spring.aop.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2022-07-30 08:49
 **/
@Aspect
public class LogAspects {

    @Pointcut("execution(* com.li.spring.aop.demo.bean..*.*(..))")
    public void pointCut() {

    }

    /**
     * 前置通知：目标方法运行之前通知
     * @param joinPoint
     */
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("before pointCut...");
        System.out.println("before pointCut..." + joinPoint.getSignature().getName() + "开始运行.....参数列表是" + Arrays.asList(joinPoint.getArgs()));

    }

    /**
     * 后置通知：目标方法运行结束之后运行，无论方法正常结束还是异常结束
     * @param joinPoint
     */
    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("after pointCut...");
        System.out.println("after pointCut..." + joinPoint.getSignature().getName() + "开始运行.....参数列表是" + Arrays.asList(joinPoint.getArgs()));
    }


    /**
     * 返回通知：目标方法正常返回之后运行
     * 注意JoinPoint joinPoint参数必须写在第一位，否则不识别
     * @param joinPoint
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("afterReturning pointCut...");
        System.out.println("afterReturning pointCut..." + joinPoint.getSignature().getName() + "开始运行.....参数列表是" + Arrays.asList(joinPoint.getArgs()));
        System.out.println("afterReturning pointCut..." + joinPoint.getSignature().getName() + "返回参数.....参数列表是" + Arrays.asList(result));
    }


    /**
     * 异常通知：目标方法发生异常以后运行
     * 注意JoinPoint joinPoint参数必须写在第一位，否则不识别
     * @param joinPoint
     */
    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        System.out.println("afterThrowing pointCut...");
        System.out.println("afterThrowing pointCut..." + joinPoint.getSignature().getName() + "开始运行.....参数列表是" + Arrays.asList(joinPoint.getArgs()));
        System.out.println("afterThrowing pointCut..." + joinPoint.getSignature().getName() + "方法异常......异常信息" + e);
    }


    /**
     * 环绕增强
     * @param joinPoint
     */
    @Around(value = "pointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around pointCut...");
        System.out.println("around pointCut..." + joinPoint.getSignature().getName() + "开始运行.....参数列表是" + Arrays.asList(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        System.out.println("around pointCut..." + joinPoint.getSignature().getName() + "返回参数.....参数列表是" + Arrays.asList(result));
    }
}
