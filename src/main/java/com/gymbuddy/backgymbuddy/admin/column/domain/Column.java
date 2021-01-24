package com.gymbuddy.backgymbuddy.admin.column.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Column {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int counts;

    private String title;

    private String contents;

    private Long columnWriterId;

    private String categoryId;

    private String imgPath;

    private String imgName;

    private String mainYn;
}
