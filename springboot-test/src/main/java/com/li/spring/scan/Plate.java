package com.li.spring.scan;

import org.springframework.stereotype.Component;

/**
 * @program: springboot-demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-22 22:59
 **/
@Component
public class Plate {

    private String flag = "1";

    public Plate() {
    }

    public Plate(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Plate{" +
                "flag='" + flag + '\'' +
                '}';
    }
}
