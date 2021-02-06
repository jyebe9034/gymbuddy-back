package com.gymbuddy.backgymbuddy.admin.history.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HistoryDto {

    private Long id;
    private LocalDateTime historyDate;
    private String title;
}
