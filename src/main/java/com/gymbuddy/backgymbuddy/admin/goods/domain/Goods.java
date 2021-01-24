package com.gymbuddy.backgymbuddy.admin.goods.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import com.gymbuddy.backgymbuddy.admin.base.Product;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Data
public class Goods extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    private Long id;

    /**
     * 굿즈 이름
     */
    private String goodsName;
}
