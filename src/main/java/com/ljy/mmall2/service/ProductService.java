package com.ljy.mmall2.service;

import com.ljy.mmall2.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljy.mmall2.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 阿拉伯
 * @since 2021-03-08
 */
public interface ProductService extends IService<Product> {

    public List<Product> findByCategoryId(String type,Integer id);
}
