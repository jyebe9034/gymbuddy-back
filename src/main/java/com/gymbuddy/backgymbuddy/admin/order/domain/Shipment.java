package com.gymbuddy.backgymbuddy.admin.order.domain;

import com.gymbuddy.backgymbuddy.admin.base.Address;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Shipment {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String phone;

    private Address address;
}
