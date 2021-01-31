package com.gymbuddy.backgymbuddy.admin.question.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "question_comment")
@Data
public class QuestionComment extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_comment_id")
    private Long id;

    @OneToOne(mappedBy = "questionComment", fetch = FetchType.LAZY)
    private Question question;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;
}
