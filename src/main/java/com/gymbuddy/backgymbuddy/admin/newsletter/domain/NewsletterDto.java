package com.gymbuddy.backgymbuddy.admin.newsletter.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NewsletterDto {

    private Long id;
    private String email;
    private LocalDate createDate;
}
