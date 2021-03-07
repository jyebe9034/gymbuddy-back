package com.gymbuddy.backgymbuddy.admin.question.domain;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class QuestionCommentDto {

    private Long id;
    private String contents;

    @Column(nullable = true)
    private String createId;
    @Column(nullable = true)
    private LocalDateTime createDate;
    @Column(nullable = true)
    private String updateId;
    @Column(nullable = true)
    private LocalDateTime updateDate;
}
