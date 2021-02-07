package com.gymbuddy.backgymbuddy.admin.goods.domain;

import com.gymbuddy.backgymbuddy.admin.base.Product;
import com.gymbuddy.backgymbuddy.admin.cart.domain.Cart;
import com.gymbuddy.backgymbuddy.admin.cart.domain.CartGoods;
import com.gymbuddy.backgymbuddy.admin.order.domain.OrderGoods;
import com.gymbuddy.backgymbuddy.admin.order.domain.OrderProgram;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "goods")
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

    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL)
    private List<OrderGoods> orderGoods = new ArrayList<>();

    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL)
    private List<CartGoods> cartGoods = new ArrayList<>();

    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL)
    private List<GoodsOption> goodsOptions = new ArrayList<>();
}
