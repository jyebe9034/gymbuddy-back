package com.gymbuddy.backgymbuddy.admin.term.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import com.gymbuddy.backgymbuddy.admin.enums.status.WebMobileStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "term")
@Data
public class Term extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(length = 300, nullable = false)
    private String imgPath;

    @Column(length = 300, nullable = false)
    private String imgName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WebMobileStatus webMobile;
}
