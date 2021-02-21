package com.gymbuddy.backgymbuddy.admin.goods.domain;

import lombok.Data;

@Data
public class GoodsDto {

    private Long id;
    private String name;
    private String colorAndSize;
    private int inventory;

    // 썸네일 이미지
    private String thumbnailImgPath;
    private String thumbnailImgName;
    // 상세 이미지
    private String detailImgPath;
    private String detailImgName;
}
