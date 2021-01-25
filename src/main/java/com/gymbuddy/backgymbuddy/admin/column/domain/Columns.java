package com.gymbuddy.backgymbuddy.admin.column.domain;

import com.gymbuddy.backgymbuddy.admin.columnWriter.domain.ColumnWriter;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "columns")
    private List<ColumnWriter> columnWriters = new ArrayList<>();

    @Column(length = 20)
    private String categoryId;

    @Column(length = 300)
    private String imgPath;

    @Column(length = 50)
    private String imgName;

    @Column(length = 1)
    private String mainYn;
}
