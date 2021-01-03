package com.gymbuddy.backgymbuddy.admin.youtube.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Youtube {

    @Id
    private Long id;

}
