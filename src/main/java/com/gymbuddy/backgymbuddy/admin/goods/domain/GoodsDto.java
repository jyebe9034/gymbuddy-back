package com.gymbuddy.backgymbuddy.admin.goods.domain;

import com.gymbuddy.backgymbuddy.admin.enums.status.GoodsStatus;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GoodsDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private String mainYn;
    private GoodsStatus status;

    private MultipartFile thumbnailFile;
    private String thumbnailImgPath;
    private String thumbnailImgName;

    private MultipartFile detailFile;
    private String detailImgPath;
    private String detailImgName;

    private List<GoodsOptionDto> optionList;
}
