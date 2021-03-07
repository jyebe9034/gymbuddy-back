package com.gymbuddy.backgymbuddy.admin.mission.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "mission")
@Data
public class Mission extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(nullable = false)
    private String contents;

    @Column(length = 300)
    private String imgPath1;

    @Column(length = 300)
    private String imgName1;

    @Column(length = 300)
    private String imgPath2;

    @Column(length = 300)
    private String imgName2;

    @Column(length = 300)
    private String imgPath3;

    @Column(length = 300)
    private String imgName3;
}
