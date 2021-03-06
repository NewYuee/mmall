package com.ljy.mmall2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljy.mmall2.entity.Cart;
import com.ljy.mmall2.entity.Product;
import com.ljy.mmall2.enums.ResultEnum;
import com.ljy.mmall2.exception.MallException;
import com.ljy.mmall2.mapper.CartMapper;
import com.ljy.mmall2.mapper.ProductMapper;
import com.ljy.mmall2.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljy.mmall2.vo.CartVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 阿拉伯
 * @since 2021-03-08
 */
@Service
@Slf4j
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public boolean save(Cart entity){
        //库存容量减
        Product product=productMapper.selectById(entity.getProductId());
        Integer stock=product.getStock()-entity.getQuantity();
        if(stock<0){
            log.error("【添加购物车】库存不足！stock="+stock);
            throw new MallException(ResultEnum.STOCK_ERROR);
        }
        product.setStock(stock);
        productMapper.updateById(product);
        if(cartMapper.insert(entity)==1)
            return true;
        return false;
    }

    @Override
    public List<CartVO> findAllCartVOByUserId(Integer id) {
        List<CartVO> cartVOList=new ArrayList<>();
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("user_id",id);
        List<Cart> cartList=cartMapper.selectList(wrapper);
        for (Cart cart : cartList) {
            CartVO cartVO=new CartVO();
            Product product=productMapper.selectById(cart.getProductId());
            BeanUtils.copyProperties(product,cartVO);
            BeanUtils.copyProperties(cart,cartVO);
            cartVO.setProductId(product.getId());
            cartVOList.add(cartVO);
        }
        return cartVOList;
    }
}
