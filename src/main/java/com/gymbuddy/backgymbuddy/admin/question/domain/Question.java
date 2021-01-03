package com.gymbuddy.backgymbuddy.admin.question.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Question {

    @Id
    private Long id;
}
