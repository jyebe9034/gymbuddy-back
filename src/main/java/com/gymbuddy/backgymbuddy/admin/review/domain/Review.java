package com.gymbuddy.backgymbuddy.admin.review.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Review {

    @Id
    private Long id;

}
