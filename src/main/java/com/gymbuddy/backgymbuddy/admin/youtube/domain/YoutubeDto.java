package com.gymbuddy.backgymbuddy.admin.youtube.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class YoutubeDto {

    private Long id;

    private String uploadDate;

    private String title;

    private String contents;

    private String link;

    // 이미지 파일
    private MultipartFile file;

    private String imgPath;

    private String imgName;

}
