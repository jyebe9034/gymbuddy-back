package com.gymbuddy.backgymbuddy.admin.question.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;
import org.hibernate.annotations.Cache;

import javax.persistence.*;

@Entity
@Data
public class QuestionComments extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_comments_id")
    private Long id;

    @OneToOne(mappedBy = "questionComments", fetch = FetchType.LAZY)
    private Question question;

    @Column(length = 300)
    private String title;

    @Column
    private String contents;
}
