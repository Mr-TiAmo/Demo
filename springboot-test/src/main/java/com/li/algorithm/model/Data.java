package com.li.algorithm.model;

/**
 * @program: demo
 * @description:
 * @author: li
 * @create: 2022-09-09 10:08
 **/
public class Data {

    private String key;
    private Integer size = 0;

    public Data(String key) {
        this.key = key;
    }

    public Data(String key, Integer size) {
        this.key = key;
        this.size = size;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
