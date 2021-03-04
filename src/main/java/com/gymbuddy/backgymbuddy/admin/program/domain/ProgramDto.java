package com.gymbuddy.backgymbuddy.admin.program.domain;

import com.gymbuddy.backgymbuddy.admin.enums.status.ProgramStatus;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProgramDto {

    private Long id;

    private String title;

    private String coach;

    private String classAddress;

    private String classDate;

    private String classTime;

    private BigDecimal price;

    private String mainYn;

    private ProgramStatus status;

    private MultipartFile thumbnailFile;

    private String thumbnailImgPath;

    private String thumbnailImgName;

    private MultipartFile detailFile;

    private String detailImgPath;

    private String detailImgName;

    private List<ProgramOptionDto> optionList;
}
