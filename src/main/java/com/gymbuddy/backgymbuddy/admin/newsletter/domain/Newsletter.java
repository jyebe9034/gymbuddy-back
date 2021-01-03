package com.gymbuddy.backgymbuddy.admin.newsletter.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Newsletter {

    @Id
    private Long id;

}
