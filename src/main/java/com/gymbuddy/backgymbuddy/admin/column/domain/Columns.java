package com.gymbuddy.backgymbuddy.admin.column.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriter;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "columns")
@Data
public class Columns extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "column_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "column_writer_id")
    private ColumnWriter columnWriter;

    @Column(length = 300, nullable = false)
    private String imgPath;

    @Column(length = 300, nullable = false)
    private String imgName;
}
