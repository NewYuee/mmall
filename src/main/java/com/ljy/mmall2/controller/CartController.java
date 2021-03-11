package com.ljy.mmall2.controller;


import com.ljy.mmall2.entity.Cart;
import com.ljy.mmall2.entity.User;
import com.ljy.mmall2.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping("/add/{productId}/{price}/{quantity}")
    public String add(
            @PathVariable("productId") Integer productId,
            @PathVariable("price") Float price,
            @PathVariable("quantity") Integer quantity,
            HttpSession session
    ){
        Cart cart=new Cart();
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        cart.setCost(price*quantity);
        User user=(User) session.getAttribute("user");
        cart.setUserId(user.getId());
        try {
            if(cartService.save(cart)){
                return "redirect:/cart/findAllcart";
            }
        } catch (Exception e) {
            return "redirect:/productCategory/list";
        }
        return null;
    }

    @GetMapping("/findAllcart")
    public ModelAndView findAllCart(HttpSession session){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("settlement1");
        User user=(User) session.getAttribute("user");
        modelAndView.addObject("list",cartService.findAllCartVOByUserId(user.getId()));
        return modelAndView;
    }
}

