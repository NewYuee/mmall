package com.ljy.mmall2.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljy.mmall2.entity.User;
import com.ljy.mmall2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 阿拉伯
 * @since 2021-03-08
 */
@Controller
@RequestMapping("/user")
public class UserController{
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(User user, Model model) {
        boolean result = false;
        try {
            result = userService.save(user);
        } catch (Exception e) {
            model.addAttribute("error",user.getLoginName()+"已存在!請重新輸入！");
            return "register";
        }
        if (result) return "login";
        return "register";
    }

    /**
     *
     * @param loginName
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(String loginName, String password, HttpSession session){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("login_name",loginName);
        wrapper.eq("password",password);
        User user=userService.getOne(wrapper);
        if(user!=null){
            session.setAttribute("user",user);
            return "redirect:/productCategory/list";
        }else {
            return "login";
        }
    }

    /**
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }
}

