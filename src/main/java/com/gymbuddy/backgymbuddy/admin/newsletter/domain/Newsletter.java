package com.gymbuddy.backgymbuddy.admin.newsletter.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "newsletter")
@Data
public class Newsletter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "newsletter_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String email;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @PrePersist
    private void prePersist() {
        createDate = LocalDateTime.now();
    }

}
