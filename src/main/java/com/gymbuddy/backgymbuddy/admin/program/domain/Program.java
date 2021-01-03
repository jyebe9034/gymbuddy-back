package com.gymbuddy.backgymbuddy.admin.program.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Program {

    @Id
    private Long id;

}
