package com.gymbuddy.backgymbuddy.admin.base;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Address {

    private String street1;

    private String street2;

    private String city;

    private String zipcode;
}
