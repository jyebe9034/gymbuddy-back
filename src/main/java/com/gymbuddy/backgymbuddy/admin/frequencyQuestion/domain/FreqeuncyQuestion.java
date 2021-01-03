package com.gymbuddy.backgymbuddy.admin.frequencyQuestion.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class FreqeuncyQuestion {

    @Id
    private Long id;

}
