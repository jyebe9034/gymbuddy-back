package com.gymbuddy.backgymbuddy.admin.review.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ReviewComments extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_comments_id")
    private Long id;

    @OneToOne(mappedBy = "reviewComments", fetch = FetchType.LAZY)
    private Review review;

    @Column(length = 100)
    private String title;

    @Column
    private String contents;
}
