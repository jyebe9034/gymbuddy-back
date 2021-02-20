package com.gymbuddy.backgymbuddy.admin.column.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ColumnsDto {

    private Long id;

    private String title;

    private String contents;

    private Long columnWriterId;

    private MultipartFile file;

    private String imgPath;

    private String imgName;

    // TODO 메인 노출여부 삭제필요...
    private String mainYn;
}
