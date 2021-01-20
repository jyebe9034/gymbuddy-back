package com.gymbuddy.backgymbuddy.admin.newsletter.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Newsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

}
