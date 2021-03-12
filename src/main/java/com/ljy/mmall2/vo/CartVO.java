package com.ljy.mmall2.vo;

import lombok.Data;

@Data
public class CartVO {
    private Integer id;
    private Integer quantity;
    private Float cost;
    private Integer productId;
    private String name;
    private String fileName;
    private Float price;
    private Integer stock;
}
