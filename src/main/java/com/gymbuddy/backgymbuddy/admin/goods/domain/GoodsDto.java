package com.gymbuddy.backgymbuddy.admin.goods.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodsDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private String mainYn;

    /*private MultipartFile thumbnailFile;
    private String thumbnailImgPath;
    private String thumbnailImgName;

    private MultipartFile detailFile;
    private String detailImgPath;
    private String detailImgName;*/

    private List<GoodsOptionDto> optionList;
}
