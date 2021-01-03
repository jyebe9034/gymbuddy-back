package com.gymbuddy.backgymbuddy.admin.anonymous.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Anonymous {

    @Id
    private Long id;

}
