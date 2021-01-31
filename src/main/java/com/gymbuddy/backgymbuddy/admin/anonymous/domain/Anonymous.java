package com.gymbuddy.backgymbuddy.admin.anonymous.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "anonymous")
@Data
public class Anonymous {

    @Id
    private Long id;

}
