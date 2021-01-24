package com.gymbuddy.backgymbuddy.admin.base;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
public abstract class Product extends BaseDomain {

    @Id @GeneratedValue
    private Long id;

    /**
     * 이미지 경로(대표이미지)
     */
    private String thumbnailImgPath;

    /**
     * 이미지 이름(대표이미지)
     */
    private String thumbnailImgName;

    /**
     * 이미지 경로(상세이미지)
     */
    private String detailImgPath;

    /**
     * 이미지 이름(상세이미지)
     */
    private String detailImgName;

    /**
     * 가격
     */
    private BigInteger price;
}
