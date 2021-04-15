package com.li.mybatisplus.service.impl;

import com.li.mybatisplus.entity.User;
import com.li.mybatisplus.mapper.UserMapper;
import com.li.mybatisplus.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 栗翱
 * @since 2021-04-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
