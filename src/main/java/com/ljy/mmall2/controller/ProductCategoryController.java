package com.ljy.mmall2.controller;


import com.ljy.mmall2.entity.User;
import com.ljy.mmall2.service.CartService;
import com.ljy.mmall2.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 阿拉伯
 * @since 2021-03-08
 */
@Controller
@RequestMapping("/productCategory")
public class ProductCategoryController {


    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private CartService cartService;
    //一个业务方法
    @GetMapping("/list")
    public ModelAndView list(HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("main");
        User user=(User)session.getAttribute("user");
        if (user==null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else {
            modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        }

        modelAndView.addObject("list",productCategoryService.getAllProductCategoryVO());
        return modelAndView;
    }
}

