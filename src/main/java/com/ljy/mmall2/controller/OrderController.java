package com.ljy.mmall2.controller;


import com.ljy.mmall2.entity.Orders;
import com.ljy.mmall2.entity.User;
import com.ljy.mmall2.service.CartService;
import com.ljy.mmall2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 阿拉伯
 * @since 2021-03-08
 */
@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/settlement3")
    public ModelAndView settlement3(Orders orders, HttpSession session,String address,String remark){
        User user =(User) session.getAttribute("user");
        orderService.save(orders,user,address,remark);
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("settlement3");
        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        modelAndView.addObject("order",orders);
        return modelAndView;
    }
}

