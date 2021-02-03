package com.gymbuddy.backgymbuddy.admin.notice.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "notice")
@Data
public class Notice extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(length = 300)
    private String imgPath;

    @Column(length = 50)
    private String imgName;

    @Column(length = 1, nullable = false)
    private String mainYn;
}
