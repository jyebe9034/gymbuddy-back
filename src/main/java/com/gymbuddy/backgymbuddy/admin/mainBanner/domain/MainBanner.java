package com.gymbuddy.backgymbuddy.admin.mainBanner.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MainBanner extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String categoryId;

    @Column
    private String link;

    @Column
    private String btnTitle;

    @Column
    private String imgPath;

    @Column
    private String imgName;

}
