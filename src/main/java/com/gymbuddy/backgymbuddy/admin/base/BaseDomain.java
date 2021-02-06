package com.gymbuddy.backgymbuddy.admin.base;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseDomain {

    @Column
    private LocalDateTime createDate;

    @Column
    private String createId;

    @Column
    private LocalDateTime updateDate;

    @Column
    private String updateId;

}
