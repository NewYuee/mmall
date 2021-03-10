package com.ljy.mmall2.controller;


import com.ljy.mmall2.service.ProductCategoryService;
import com.ljy.mmall2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 阿拉伯
 * @since 2021-03-08
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list/{type}/{id}")
    public ModelAndView list(@PathVariable("type") String type, @PathVariable("id")  Integer id){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("productList");
        modelAndView.addObject("list",productCategoryService.getAllProductCategoryVO());
        modelAndView.addObject("productList",productService.findByCategoryId(type,id));
        return modelAndView;
    }
}

