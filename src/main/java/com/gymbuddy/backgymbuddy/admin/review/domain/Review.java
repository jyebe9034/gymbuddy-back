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
    private String categoryId;

    /**
     * 제목
     */
    private String title;

    /**
     * 내용
     */
    private String contents;

    /**
     * 별점
     */
    private float rating;

}
