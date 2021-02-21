package com.gymbuddy.backgymbuddy.admin.banner.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BannerDto {

    private Long id;

    private String title;

    private String categoryId;

    private String link;

    private String btnTitle;

    // 이미지 파일
    private MultipartFile file;

    private String imgPath;

    private String imgName;

}
