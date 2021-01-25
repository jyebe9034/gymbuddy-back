package com.gymbuddy.backgymbuddy.admin.newsletter.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Newsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "newsletter_id")
    private Long id;

    @Column(length = 300)
    private String email;

}
