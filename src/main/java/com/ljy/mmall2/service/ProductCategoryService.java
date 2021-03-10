package com.ljy.mmall2.service;

import com.ljy.mmall2.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ljy.mmall2.vo.ProductCategoryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 阿拉伯
 * @since 2021-03-08
 */
public interface ProductCategoryService extends IService<ProductCategory> {
    public List<ProductCategoryVO> getAllProductCategoryVO();

}
