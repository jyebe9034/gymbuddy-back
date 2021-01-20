package com.gymbuddy.backgymbuddy.admin.base;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
public abstract class BaseDomain {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime createDate;

    private String createId;

    private LocalDateTime updateDate;

    private String updateId;

}
