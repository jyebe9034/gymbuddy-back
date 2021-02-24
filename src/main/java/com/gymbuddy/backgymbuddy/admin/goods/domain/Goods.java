package com.gymbuddy.backgymbuddy.admin.goods.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.gymbuddy.backgymbuddy.admin.base.Product;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "goods")
@ToString(exclude = "goods")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Data
public class Goods extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    private Long id;

    /**
     * 굿즈 이름
     */
    @Column(length = 200, nullable = false)
    private String name;

    /**
     * 가격
     */
    @Column(length = 20, nullable = false)
    private BigDecimal price;

    /**
     * 메인 노출 여부
     */
    @Column
    private String mainYn;

    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL)
    private List<GoodsOption> goodsOptions = new ArrayList<>();

//    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL)
//    private List<OrderGoods> orderGoods = new ArrayList<>();
//
//    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL)
//    private List<CartGoods> cartGoods = new ArrayList<>();
}
