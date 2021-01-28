package com.gymbuddy.backgymbuddy.admin.column.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriter;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Data
public class Columns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "column_id")
    private Long id;

    @Column
    private int counts;

    @Column(length = 100)
    private String title;

    @Column
    private String contents;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "column_writer_id")
    private ColumnWriter columnWriter;

    @Column(length = 20)
    private String categoryId;

    @Column(length = 300)
    private String imgPath;

    @Column(length = 50)
    private String imgName;

    @Column(length = 1)
    private String mainYn;
}
