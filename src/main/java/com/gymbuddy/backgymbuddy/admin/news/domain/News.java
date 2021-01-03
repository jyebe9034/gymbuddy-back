package com.gymbuddy.backgymbuddy.admin.news.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class News {

    @Id
    private Long id;

}
