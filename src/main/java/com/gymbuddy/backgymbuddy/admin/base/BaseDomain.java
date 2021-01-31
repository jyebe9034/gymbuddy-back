package com.gymbuddy.backgymbuddy.admin.base;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public abstract class BaseDomain {

    private LocalDateTime createDate;

    private String createId;

    private LocalDateTime updateDate;

    private String updateId;

}
