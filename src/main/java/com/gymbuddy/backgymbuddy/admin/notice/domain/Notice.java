package com.gymbuddy.backgymbuddy.admin.notice.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Notice extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    private String categoryId;

    @Column
    private String contents;

    @Column
    private String imgPath;

    @Column
    private String imgName;

    private String mainYn;
}
