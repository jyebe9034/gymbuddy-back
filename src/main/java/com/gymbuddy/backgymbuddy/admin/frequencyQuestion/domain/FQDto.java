package com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain;

import com.gymbuddy.backgymbuddy.admin.enums.category.FaqEnum;
import lombok.Data;

@Data
public class FQDto {

    private Long id;
    private FaqEnum categoryId;
    private String title;
    private String contents;
}
