package com.gymbuddy.backgymbuddy.admin.member.domain;

import com.gymbuddy.backgymbuddy.admin.enums.status.WebMobileStatus;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MemberDto {

    private Long id;
    private MultipartFile file;
    private String imgName;
    private String imgPath;
    private WebMobileStatus webMobile;
}
