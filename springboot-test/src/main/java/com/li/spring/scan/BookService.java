package com.li.spring.scan;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @program: springboot-demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-15 23:16
 **/
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    @Inject
//    @Resource(name = "plate")
//    @Qualifier("plate")
//    @Autowired
    private Plate plate;
}
