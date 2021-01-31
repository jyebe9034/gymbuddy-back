package com.gymbuddy.backgymbuddy.admin.question.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "question")
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(length = 300)
    private String imgPath1;

    @Column(length = 50)
    private String imgName1;

    @Column(length = 300)
    private String imgPath2;

    @Column(length = 50)
    private String imgName2;

    @Column(length = 300)
    private String imgPath3;

    @Column(length = 50)
    private String imgName3;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_comment_id")
    private QuestionComment questionComment;
}
