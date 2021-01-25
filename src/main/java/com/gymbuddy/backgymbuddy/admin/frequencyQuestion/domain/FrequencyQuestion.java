package com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class FrequencyQuestion extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "frequency_question_id")
    private Long id;

    @Column(length = 20)
    private String categoryId;

    @Column(length = 300)
    private String title;

    @Column
    private String contents;

}
