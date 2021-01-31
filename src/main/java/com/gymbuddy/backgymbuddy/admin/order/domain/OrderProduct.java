package com.gymbuddy.backgymbuddy.admin.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymbuddy.backgymbuddy.admin.base.Product;
import com.gymbuddy.backgymbuddy.admin.goods.domain.Goods;
import com.gymbuddy.backgymbuddy.admin.program.domain.Program;
import lombok.Data;

import javax.persistence.*;

import java.math.BigInteger;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "order_product")
@Data
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "goods_id")
    private Goods goods;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "program_id")
    private Program program;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @Column
    private BigInteger orderPrice; // 주문 가격

    @Column
    private int count; // 주문 수량
}
