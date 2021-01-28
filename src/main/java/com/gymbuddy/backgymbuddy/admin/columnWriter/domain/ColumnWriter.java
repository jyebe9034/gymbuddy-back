package com.gymbuddy.backgymbuddy.admin.columnWriter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymbuddy.backgymbuddy.admin.column.domain.Columns;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "columnWriter", cascade = CascadeType.ALL)
    private List<Columns> columns = new ArrayList<>();
}
