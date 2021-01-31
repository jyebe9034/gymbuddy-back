package com.gymbuddy.backgymbuddy.admin.review.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "review")
@Data
public class Review extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    /**
     * 카테고리
     */
    @Column(length = 20, nullable = false)
    private String categoryId;

    /**
     * 제목
     */
    @Column(length = 100, nullable = false)
    private String title;

    /**
     * 내용
     */
    @Column(nullable = false)
    private String contents;

    /**
     * 별점
     */
    @Column(nullable = false)
    private float rating;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "review_comment_id")
    private ReviewComment reviewComment;
}
