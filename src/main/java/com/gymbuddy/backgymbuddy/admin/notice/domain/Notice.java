package com.gymbuddy.backgymbuddy.admin.notice.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Notice {

    @Id
    private Long id;

}
