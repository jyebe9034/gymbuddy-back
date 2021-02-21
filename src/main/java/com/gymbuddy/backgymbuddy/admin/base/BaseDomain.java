package com.gymbuddy.backgymbuddy.admin.base;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseDomain {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @CreatedBy
    @Column(updatable = false)
    private String createId;

    @LastModifiedDate
    private LocalDateTime updateDate;

    @LastModifiedBy
    private String updateId;

    @PrePersist
    private void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createDate = now;
        updateDate = now;
    }

    @PreUpdate
    private void preUpdate() {
        updateDate = LocalDateTime.now();
    }

}
