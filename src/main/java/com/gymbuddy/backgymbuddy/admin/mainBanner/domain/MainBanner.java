package com.gymbuddy.backgymbuddy.admin.mainBanner.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class MainBanner {

    @Id
    private Long id;
}
