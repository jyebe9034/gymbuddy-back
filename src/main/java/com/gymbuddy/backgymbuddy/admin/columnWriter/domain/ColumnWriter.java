package com.gymbuddy.backgymbuddy.admin.columnWriter.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ColumnWriter {

    @Id
    private Long id;

}
