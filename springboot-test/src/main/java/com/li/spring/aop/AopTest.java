package com.li.spring.aop;
;
import com.li.spring.aop.demo.AOPConfig;
import com.li.spring.aop.demo.bean.Book;
import com.li.spring.aop.demo.bean.Print;
import com.li.spring.aop.demo.bean.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: Demo
 * @description:
 * @author: 栗翱
 * @create: 2022-07-30 08:43
 **/
public class AopTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AOPConfig.class);
        Print book = applicationContext.getBean(Print.class);
//        Student student = applicationContext.getBean(Student.class);
        book.run();
//        book.add(1);
//        student.put();
    }
}
