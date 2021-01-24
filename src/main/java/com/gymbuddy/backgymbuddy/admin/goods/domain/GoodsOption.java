package com.gymbuddy.backgymbuddy.admin.goods.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class GoodsOption extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_option_id")
    private Long id;

    /**
     * 색상 & 사이즈
     */
    private String colorAndSize;

    /**
     * 재고
     */
    private String inventory;
}
