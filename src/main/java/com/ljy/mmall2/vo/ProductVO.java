package com.ljy.mmall2.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//有参构造注解
@AllArgsConstructor
public class ProductVO {
    private Integer id;
    private String name;
    private Float price;
    private String fileName;

}
