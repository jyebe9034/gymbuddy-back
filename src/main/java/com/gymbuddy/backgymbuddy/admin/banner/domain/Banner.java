package com.gymbuddy.backgymbuddy.admin.banner.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Banner extends BaseDomain {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id")
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(length = 20)
    private String categoryId;

    @Column(length = 500)
    private String link;

    @Column(length = 20)
    private String btnTitle;

    @Column(length = 300)
    private String imgPath;

    @Column(length = 50)
    private String imgName;

}
