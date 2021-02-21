package com.gymbuddy.backgymbuddy.admin.banner.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import com.gymbuddy.backgymbuddy.admin.enums.category.BannerEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "banner")
@Data
public class Banner extends BaseDomain {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banner_id")
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String title;

    @Column(length = 10, nullable = false)
    private String categoryId;

    @Column(length = 500)
    private String link;

    @Column(length = 20)
    private String btnTitle;

    @Column(length = 300, nullable = false)
    private String imgPath;

    @Column(length = 50, nullable = false)
    private String imgName;

}
