package com.gymbuddy.backgymbuddy.admin.goods.domain;

import com.gymbuddy.backgymbuddy.admin.base.Product;
import com.gymbuddy.backgymbuddy.admin.cart.domain.Cart;
import com.gymbuddy.backgymbuddy.admin.order.domain.OrderProduct;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "goods")
@Data
@DiscriminatorValue("GODDS")
public class Goods extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    private Long id;

    /**
     * 굿즈 이름
     */
    @Column(length = 200, nullable = false)
    private String goodsName;

    @OneToMany(mappedBy = "goods", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProduct = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
