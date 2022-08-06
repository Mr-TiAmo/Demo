package com.li.spring.transaction.bean;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2022-07-31 09:09
 **/
@Service
public class TransactionServiceImpl implements TransactionService {
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void print() {
        int i = 1/0;
    }
}
