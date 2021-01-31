package com.gymbuddy.backgymbuddy.admin.youtube.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "youtube")
@Data
public class Youtube {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "youtube_id")
    private Long id;

    @Column
    private String uploadDate;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(length = 500, nullable = false)
    private String link;

    @Column(length = 20, nullable = false)
    private String categoryId;

    @Column(length = 300, nullable = false)
    private String imgPath;

    @Column(length = 50, nullable = false)
    private String imgName;

    @Column(length = 1, nullable = false)
    private String mainYn;

}
