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
    @Column(name = "term_id")
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(length = 300)
    private String imgPath;

    @Column(length = 50)
    private String imgName;

    @Enumerated(EnumType.STRING)
    private WebMobile webOrMobile;
}
