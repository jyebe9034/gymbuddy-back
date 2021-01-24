package com.gymbuddy.backgymbuddy.admin.youtube.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Youtube {

    @Id
    private Long id;

    private String title;

    private String contents;

    @Column
    private String link;

    private String categoryId;

    private String imgPath;

    private String imgName;

    private String mainYn;
}
