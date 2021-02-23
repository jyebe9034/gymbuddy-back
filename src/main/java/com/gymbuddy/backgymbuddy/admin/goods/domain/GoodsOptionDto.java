package com.gymbuddy.backgymbuddy.admin.goods.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsOptionDto {

    private Long id;
    private String colorAndSize;
    private int inventory;
    private BigDecimal extraPrice;
}
