package com.gymbuddy.backgymbuddy.admin.question.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import com.gymbuddy.backgymbuddy.admin.enums.category.QuestionEnum;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
@Data
@ToString(exclude = "question")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Question extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionEnum categoryId;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(length = 300)
    private String imgPath1;

    @Column(length = 300)
    private String imgName1;

    @Column(length = 300)
    private String imgPath2;

    @Column(length = 300)
    private String imgName2;

    @Column(length = 300)
    private String imgPath3;

    @Column(length = 300)
    private String imgName3;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<QuestionComment> questionCommentList = new ArrayList<>();
}
