package com.gymbuddy.backgymbuddy.admin.review.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Review extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    /**
     * 카테고리
     */
    @Column(length = 20)
    private String categoryId;

    /**
     * 제목
     */
    @Column(length = 300)
    private String title;

    /**
     * 내용
     */
    @Column
    private String contents;

    /**
     * 별점
     */
    @Column
    private float rating;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "review_comments_id")
    private ReviewComments reviewComments;
}
