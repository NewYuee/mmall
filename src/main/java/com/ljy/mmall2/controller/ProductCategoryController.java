package com.ljy.mmall2.controller;


import com.ljy.mmall2.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

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

    //一个业务方法
    @GetMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("main");
        modelAndView.addObject("list",productCategoryService.getAllProductCategoryVO());
        return modelAndView;
    }
}

