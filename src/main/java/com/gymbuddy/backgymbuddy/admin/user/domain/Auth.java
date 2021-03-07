package com.gymbuddy.backgymbuddy.admin.user.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "auth")
@Data
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private Long id;

    /**
     * 이메일
     */
    @Column(length = 100)
    private String email;

    /**
     * 인증번호
     */
    @Column(length = 20)
    private String authNum;
}
