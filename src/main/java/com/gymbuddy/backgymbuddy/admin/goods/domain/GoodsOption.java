package com.gymbuddy.backgymbuddy.admin.goods.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "goods_option")
@Data
public class GoodsOption extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_option_id")
    private Long id;

    /**
     * 색상 & 사이즈
     */
    @Column(length = 100, nullable = false)
    private String colorAndSize;

    /**
     * 재고
     */
    @Column(nullable = false)
    private int inventory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;
}
