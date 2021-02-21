package com.gymbuddy.backgymbuddy.admin.question.domain;

import com.gymbuddy.backgymbuddy.admin.enums.category.QuestionEnum;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class QuestionDto {

    private Long id;
    private String title;
    private String contents;
    private QuestionEnum categoryId;
    private MultipartFile file1;
    private MultipartFile file2;
    private MultipartFile file3;
    private String imgPath1;
    private String imgName1;
    private String imgPath2;
    private String imgName2;
    private String imgPath3;
    private String imgName3;
}
