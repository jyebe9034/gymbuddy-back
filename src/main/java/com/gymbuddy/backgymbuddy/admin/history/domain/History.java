package com.gymbuddy.backgymbuddy.admin.history.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class History {

    @Id
    private Long id;

}
