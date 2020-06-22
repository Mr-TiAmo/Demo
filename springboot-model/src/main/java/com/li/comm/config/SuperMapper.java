package com.li.comm.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @program: demo
 * @description:
 * @author: 栗翱
 * @create: 2020-06-17 17:23
 **/
public interface SuperMapper<T> extends BaseMapper<T> {

    //自定义通用方法
    int myInsertAll(T entity);

}