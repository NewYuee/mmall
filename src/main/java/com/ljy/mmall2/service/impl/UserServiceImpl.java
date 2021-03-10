package com.ljy.mmall2.service.impl;

import com.ljy.mmall2.entity.User;
import com.ljy.mmall2.mapper.UserMapper;
import com.ljy.mmall2.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 阿拉伯
 * @since 2021-03-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
