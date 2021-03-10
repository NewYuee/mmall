package com.ljy.mmall2.vo;

import com.ljy.mmall2.entity.Product;
import lombok.Data;

import java.util.List;

/**
 * 关于几级分类的映射
 */
@Data   //lombok的注解
public class ProductCategoryVO {

    private Integer id;
    private String name;
    private List<ProductCategoryVO> children;
    private String bannerImg;
    private String topImg;
    private List<ProductVO> productVOList;

    public ProductCategoryVO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductCategoryVO> getChildren() {
        return children;
    }

    public void setChildren(List<ProductCategoryVO> children) {
        this.children = children;
    }
}
