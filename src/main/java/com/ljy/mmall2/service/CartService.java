package com.ljy.mmall2.service;

import com.ljy.mmall2.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljy.mmall2.vo.CartVO;
import org.apache.catalina.LifecycleState;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 阿拉伯
 * @since 2021-03-08
 */
public interface CartService extends IService<Cart> {
    public List<CartVO> findAllCartVOByUserId(Integer id);
}
