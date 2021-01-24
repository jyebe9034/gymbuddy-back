package com.gymbuddy.backgymbuddy.admin.review.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class ReviewComments {

    @Id @GeneratedValue
    private Long id;

    private Long review_id;

    private String title;

    private String contents;
}
