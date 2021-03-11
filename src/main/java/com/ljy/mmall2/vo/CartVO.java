package com.ljy.mmall2.vo;

import lombok.Data;

@Data
public class CartVO {
    private Integer cartId;
    private Integer quantity;
    private Float cost;
    private String name;
    private String fileName;
    private Float price;
}
