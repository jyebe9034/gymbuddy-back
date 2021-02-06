package com.gymbuddy.backgymbuddy.admin.businessIdentity.domain;

import com.gymbuddy.backgymbuddy.admin.enums.status.WebMobileStatus;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BiDto {

    private Long id;
    private MultipartFile file;
    private String imgPath;
    private String imgName;
    private WebMobileStatus webMobile;
}
