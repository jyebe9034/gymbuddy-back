package com.gymbuddy.backgymbuddy.admin.mission.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Mission extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String contents;

    @Column
    private String imgPath1;

    @Column
    private String imgName1;

    @Column
    private String imgPath2;

    @Column
    private String imgName2;

    @Column
    private String imgPath3;

    @Column
    private String imgName3;
}
