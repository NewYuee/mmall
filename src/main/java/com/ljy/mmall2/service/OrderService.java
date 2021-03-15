package com.ljy.mmall2.service;

import com.ljy.mmall2.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljy.mmall2.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 阿拉伯
 * @since 2021-03-08
 */
public interface OrderService extends IService<Orders> {

    public  boolean save(Orders orders, User user,String address,String remark);

}
