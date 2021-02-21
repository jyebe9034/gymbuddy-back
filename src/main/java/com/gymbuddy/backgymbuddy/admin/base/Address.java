package com.gymbuddy.backgymbuddy.admin.base;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
public class Address {

    /**
     * 우편번호
     */
    private String zipcode;

    /**
     * 주소1(도, 시, 도로명 etc)
     */
    private String street1;

    /**
     * 주소2(상세주소 - 몇동 몇호)
     */
    private String street2;

    public Address() {}
}
