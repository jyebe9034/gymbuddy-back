package com.gymbuddy.backgymbuddy.admin.news.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NewsDto {

    private Long id;

    private String title;

    private String contents;

    // 이미지 파일
    private MultipartFile file;

    private String imgPath;

    private String imgName;
}
