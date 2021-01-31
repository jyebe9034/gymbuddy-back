package com.gymbuddy.backgymbuddy.admin.base;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigInteger;

@Data
public abstract class Product extends BaseDomain {

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
}
