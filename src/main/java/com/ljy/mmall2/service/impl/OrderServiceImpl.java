package com.ljy.mmall2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljy.mmall2.entity.*;
import com.ljy.mmall2.mapper.*;
import com.ljy.mmall2.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 阿拉伯
 * @since 2021-03-08
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderMapper orderMapper;
    @Override
    public boolean save(Orders orders, User user,String address,String remark) {
        //添加新的地址
        if(orders.getUserAddress().equals("newAddress")){
            //存入数据库
            UserAddress userAddress=new UserAddress();
            userAddress.setAddress(address);
            userAddress.setRemark(remark);
            userAddress.setIsdefault(1);
            userAddress.setUserId(user.getId());
            //修改默认地址
            QueryWrapper wrapper=new QueryWrapper();
            wrapper.eq("isdefault",1);
            wrapper.eq("user_id",user.getId());
            UserAddress oldDefault=userAddressMapper.selectOne(wrapper);
            if (oldDefault == null) {
                userAddressMapper.insert(userAddress);
                orders.setUserAddress(address);
            } else {
                oldDefault.setIsdefault(0);
                userAddressMapper.updateById(oldDefault);
                userAddressMapper.insert(userAddress);
                //新地址的值赋给orders
                orders.setUserAddress(address);
            }
        }
        //存储orders
        orders.setUserId(user.getId());
        orders.setLoginName(user.getLoginName());
        String seriaNumber=null;
        try{
            StringBuffer result=new StringBuffer();
            for (int i=0;i<32;i++){
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            seriaNumber=result.toString().toUpperCase();
        }catch (Exception e){
            e.printStackTrace();
        }
        orders.setSerialnumber(seriaNumber);

        orderMapper.insert(orders);
        //存储ordersdetail
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("user_id",user.getId());
        List<Cart> cartList=cartMapper.selectList(wrapper);
        List<OrderDetail> orderDetailList=new ArrayList<>();
        for (Cart cart:cartList){
            OrderDetail orderDetail=new OrderDetail();
            BeanUtils.copyProperties(cart,orderDetail);
            orderDetail.setId(null);
            orderDetail.setOrderId(orders.getId());
            orderDetailMapper.insert(orderDetail);
        }

        //清空购物车
        QueryWrapper wrapper1=new QueryWrapper();
        wrapper1.eq("user_id",user.getId());
        cartMapper.delete(wrapper1);
        return true;
    }
}
