package com.gymbuddy.backgymbuddy.admin.term.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import com.gymbuddy.backgymbuddy.admin.base.WebMobile;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Term extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String imgPath;

    @Column
    private String imgName;

    private WebMobile webOrMobile;
}
