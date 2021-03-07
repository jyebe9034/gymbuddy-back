package com.gymbuddy.backgymbuddy.admin.column.domain;

import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriterDto;
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

    private ColumnWriterDto columnWriter;
}
