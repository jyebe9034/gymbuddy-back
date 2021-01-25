package com.gymbuddy.backgymbuddy.admin.columnWriter.domain;

import com.gymbuddy.backgymbuddy.admin.column.domain.Columns;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ColumnWriter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "column_writer_id")
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String job;

    @Column
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_id")
    private Columns columns;
}
