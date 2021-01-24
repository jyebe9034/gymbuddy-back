package com.gymbuddy.backgymbuddy.admin.columnWriter.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ColumnWriter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "column_writer_id")
    private Long id;

    private String name;

    private String job;

    private String contents;
}
