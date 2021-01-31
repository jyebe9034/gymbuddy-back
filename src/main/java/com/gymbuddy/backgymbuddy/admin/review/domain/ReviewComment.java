package com.gymbuddy.backgymbuddy.admin.review.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "review_comment")
@Data
public class ReviewComment extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_comment_id")
    private Long id;

    @OneToOne(mappedBy = "reviewComment", fetch = FetchType.LAZY)
    private Review review;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;
}
