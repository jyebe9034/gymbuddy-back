package com.gymbuddy.backgymbuddy.admin.term.domain;

import com.gymbuddy.backgymbuddy.admin.enums.status.WebMobileStatus;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TermDto {

    private Long id;
    private String title;
    private MultipartFile file;
    private String imgPath;
    private String imgName;
    private WebMobileStatus webMobile;
}
