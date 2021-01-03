package com.gymbuddy.backgymbuddy.admin.businessIdentity.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class BusinessIdentity {

    @Id
    private Long id;

}
