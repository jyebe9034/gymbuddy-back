package com.gymbuddy.backgymbuddy.admin.columnWriter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import com.gymbuddy.backgymbuddy.admin.column.domain.Columns;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "column_writer")
@Data
public class ColumnWriter extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "column_writer_id")
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String job;

    @Column(nullable = false)
    private String contents;

    @OneToMany(mappedBy = "columnWriter", cascade = CascadeType.ALL)
    private List<Columns> columns = new ArrayList<>();
}
