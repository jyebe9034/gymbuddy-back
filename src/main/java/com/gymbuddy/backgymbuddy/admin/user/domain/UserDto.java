package com.gymbuddy.backgymbuddy.admin.user.domain;

import lombok.Data;

@Data
public class UserDto {

    /**
     * JPA생성 아이디
     */
    private Long id;

    /**
     * 회원 등급
     */
    private String grade;

    /**
     * 아이디
     */
    private String identity;

    /**
     * 이메일
     */
    private String email;

    /**
     * 비밀번호
     */
    private String password;

    /**
     * 이름
     */
    private String name;

    /**
     * 전화번호
     */
    private String phone;

    /**
     * 우편번호
     */
    private String zipcode;

    /**
     * 주소
     */
    private String street1;

    /**
     * 상세 주소
     */
    private String street2;

    /**
     * 광고 및 뉴스레터 수신동의
     */
    private String agreeYn;

}
