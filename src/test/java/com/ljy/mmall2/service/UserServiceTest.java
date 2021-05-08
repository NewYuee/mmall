package com.ljy.mmall2.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljy.mmall2.entity.User;
import com.ljy.mmall2.enums.GenderEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    void test(){
        User user=new User();
        user.setLoginName("js22saa");
        user.setGenderCode(1);
        user.setPassword("123456");
        user.setUserName("2rf2a");
        System.out.println(userService.save(user));
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("login_name","js22saa");
        System.out.println(userService.getOne(wrapper));


    }

    @Test
    void test2(){
/*        User user =userService.getById(27);
        user.setUserName("gg");
        System.out.println(userService.updateById(user));*/
    }
}