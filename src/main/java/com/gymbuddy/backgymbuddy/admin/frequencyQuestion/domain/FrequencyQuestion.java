package com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import com.gymbuddy.backgymbuddy.admin.enums.category.FaqEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "frequency_question")
@Data
public class FrequencyQuestion extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "frequency_question_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private FaqEnum categoryId;

    @Column(length = 300, nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

}
