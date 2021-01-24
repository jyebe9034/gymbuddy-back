package com.gymbuddy.backgymbuddy.admin.news.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class News extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private String categoryId;

    @Column
    private String imgPath;

    @Column
    private String imgName;

    private String mainYn;

}
