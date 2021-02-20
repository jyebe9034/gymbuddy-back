package com.gymbuddy.backgymbuddy.admin.program.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProgramOptionDto {

    private Long id;

    private String classDateTime;

    private int userCount;

    private BigDecimal addPrice;
}
