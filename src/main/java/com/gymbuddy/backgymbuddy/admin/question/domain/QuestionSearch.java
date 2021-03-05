package com.gymbuddy.backgymbuddy.admin.question.domain;

import com.gymbuddy.backgymbuddy.admin.enums.category.QuestionEnum;
import lombok.Data;

import javax.persistence.Column;

@Data
public class QuestionSearch {

    private String categoryId;

    @Column(nullable = true)
    private String title;

    private String keyword;

    private String type;

    @Column(nullable = true)
    private String createId;


}
