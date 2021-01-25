package com.gymbuddy.backgymbuddy.admin.businessIdentity.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import com.gymbuddy.backgymbuddy.admin.base.WebMobile;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BusinessIdentity extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_identity_id")
    private Long id;

    @Column(length = 300)
    private String imgPath;

    @Column(length = 50)
    private String imgName;

    @Enumerated(EnumType.STRING)
    private WebMobile webOrMobile;

}
