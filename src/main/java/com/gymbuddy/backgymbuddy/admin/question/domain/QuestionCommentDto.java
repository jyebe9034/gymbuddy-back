package com.gymbuddy.backgymbuddy.admin.question.domain;

import lombok.Data;

@Data
public class QuestionCommentDto {

    private Long question_comment_id;
    private Question question;
    private String title;
    private String contents;
}
