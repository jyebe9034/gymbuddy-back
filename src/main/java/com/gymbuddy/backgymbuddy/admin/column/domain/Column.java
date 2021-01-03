package com.gymbuddy.backgymbuddy.admin.column.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Column {

    @Id
    private Long id;

}
