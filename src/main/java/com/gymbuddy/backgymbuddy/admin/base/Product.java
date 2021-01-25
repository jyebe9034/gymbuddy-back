package com.gymbuddy.backgymbuddy.admin.base;

import com.gymbuddy.backgymbuddy.admin.cart.domain.Cart;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@Data
public abstract class Product extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    /**
     * 이미지 경로(대표이미지)
     */
    @Column(length = 300)
    private String thumbnailImgPath;

    /**
     * 이미지 이름(대표이미지)
     */
    @Column(length = 50)
    private String thumbnailImgName;

    /**
     * 이미지 경로(상세이미지)
     */
    @Column(length = 300)
    private String detailImgPath;

    /**
     * 이미지 이름(상세이미지)
     */
    @Column(length = 50)
    private String detailImgName;

    /**
     * 가격
     */
    @Column
    private BigInteger price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
