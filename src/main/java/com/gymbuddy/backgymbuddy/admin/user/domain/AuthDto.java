package com.gymbuddy.backgymbuddy.admin.user.domain;

import lombok.Data;

@Data
public class AuthDto {

    private Long id;

    private String email;

    private String authNum;
}
