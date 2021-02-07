package com.gymbuddy.backgymbuddy.admin.history.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "history")
@Data
public class History extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    @Column(nullable = false)
    private String historyDate;

    @Column(length = 150, nullable = false)
    private String title;

}
