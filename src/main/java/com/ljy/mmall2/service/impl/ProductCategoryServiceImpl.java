package com.ljy.mmall2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljy.mmall2.entity.Product;
import com.ljy.mmall2.entity.ProductCategory;
import com.ljy.mmall2.mapper.ProductCategoryMapper;
import com.ljy.mmall2.mapper.ProductMapper;
import com.ljy.mmall2.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljy.mmall2.service.ProductService;
import com.ljy.mmall2.vo.ProductCategoryVO;
import com.ljy.mmall2.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 阿拉伯
 * @since 2021-03-08
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<ProductCategoryVO> getAllProductCategoryVO() {

//        //1.将实体类转成VO
//        List<ProductCategory> productCategoryList=productCategoryMapper.selectList(null);
//        //2.VO
//        List<ProductCategoryVO> productCategoryVOList=new ArrayList<>();
//        for(ProductCategory productCategory:productCategoryList){
//            ProductCategoryVO productCategoryVO=new ProductCategoryVO();
//            //sprng 的工具类复制属性copy
//            BeanUtils.copyProperties(productCategory,productCategoryVO);
////            productCategoryVO.setName(productCategory.getName());
////            productCategoryVO.setId(productCategory.getId());
//            productCategoryVOList.add(productCategoryVO);
//        }
        //查询一级分类
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("type",1);
        List<ProductCategory> levelOne=productCategoryMapper.selectList(wrapper);
        //通过Stream流
        List<ProductCategoryVO> levelOneVO=levelOne.stream().map(e -> new ProductCategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
        //图片赋值
        //商品信息赋值
        for (int i = 0; i <levelOneVO.size() ; i++) {
            levelOneVO.get(i).setBannerImg("/images/banner"+i+".png");
            levelOneVO.get(i).setTopImg("/images/top"+i+".png");
            wrapper=new QueryWrapper();
            wrapper.eq("categorylevelone_id",levelOneVO.get(i).getId());
            List<Product> productList= productMapper.selectList(wrapper);
            List<ProductVO> productVOList=productList.stream()
                    .map(e -> new ProductVO(
                            e.getId(),
                            e.getName(),
                            e.getPrice(),
                            e.getFileName()
                    )).collect(Collectors.toList());
            levelOneVO.get(i).setProductVOList(productVOList);
        }
    for (ProductCategoryVO levelOneproductCategoryVO : levelOneVO) {
            wrapper=new QueryWrapper();
            wrapper.eq("type",2);
            wrapper.eq("parent_id",levelOneproductCategoryVO.getId());
            List<ProductCategory> levelTwo=productCategoryMapper.selectList(wrapper);
            List<ProductCategoryVO> levelTwoVO=levelTwo.stream().map(e -> new ProductCategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
            levelOneproductCategoryVO.setChildren(levelTwoVO);
            for (ProductCategoryVO levelTwoproductCategoryVO : levelTwoVO) {
                wrapper=new QueryWrapper();
                wrapper.eq("type",3);
                wrapper.eq("parent_id",levelTwoproductCategoryVO.getId());
                List<ProductCategory> levelThree=productCategoryMapper.selectList(wrapper);
                List<ProductCategoryVO> levelThreeVO=levelThree.stream().map(e -> new ProductCategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
                levelTwoproductCategoryVO.setChildren(levelThreeVO);
            }
        }
        return levelOneVO;
    }
}
