package com.gymbuddy.backgymbuddy.admin.news.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "news")
@Data
public class News extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private Long id;

    @Column(length = 100,nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(length = 300)
    private String imgPath;

    @Column(length = 300)
    private String imgName;
}
