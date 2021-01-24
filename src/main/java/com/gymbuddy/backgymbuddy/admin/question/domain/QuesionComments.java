package com.gymbuddy.backgymbuddy.admin.question.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class QuesionComments {

    @Id @GeneratedValue
    private Long id;

    private Long question_id;

    private String title;

    private String contents;
}
