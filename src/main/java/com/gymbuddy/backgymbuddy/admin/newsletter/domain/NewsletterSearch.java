package com.gymbuddy.backgymbuddy.admin.newsletter.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class NewsletterSearch {

    private String start;
    private String end;
}
