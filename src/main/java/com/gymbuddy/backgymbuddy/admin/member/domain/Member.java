package com.gymbuddy.backgymbuddy.admin.member.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import com.gymbuddy.backgymbuddy.admin.enums.status.WebMobileStatus;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Data
public class Member extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 300, nullable = false)
    private String imgPath;

    @Column(length = 300, nullable = false)
    private String imgName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WebMobileStatus webMobile;

}
