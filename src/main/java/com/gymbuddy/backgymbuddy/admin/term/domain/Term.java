package com.gymbuddy.backgymbuddy.admin.term.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Term {

    @Id
    private Long id;

}
