package com.li.mybatisplus.mapper;

import com.li.mybatisplus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 栗翱
 * @since 2021-04-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
