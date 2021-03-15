package com.ljy.mmall2.service;

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
        user.setLoginName("jss");
        user.setGender(GenderEnum.MAN);
        user.setPassword("123");
        user.setUserName("2rf");
        System.out.println(userService.save(user));
    }

    @Test
    void test2(){
/*        User user =userService.getById(27);
        user.setUserName("gg");
        System.out.println(userService.updateById(user));*/
    }
}