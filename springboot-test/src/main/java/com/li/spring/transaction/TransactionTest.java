package com.li.spring.transaction;

import com.li.spring.transaction.bean.TransactionService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2022-07-31 09:07
 **/
public class TransactionTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TransactionConfig.class);
        TransactionService transactionService = context.getBean(TransactionService.class);
        transactionService.print();
    }
}
