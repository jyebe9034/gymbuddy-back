package com.gymbuddy.backgymbuddy.admin.youtube.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Youtube {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "youtube_id")
    private Long id;

    @Column
    private String uploadDate;

    @Column(length = 100)
    private String title;

    @Column
    private String contents;

    @Column(length = 500)
    private String link;

    @Column(length = 20)
    private String categoryId;

    @Column(length = 300)
    private String imgPath;

    @Column(length = 50)
    private String imgName;

    @Column(length = 1)
    private String mainYn;

}
