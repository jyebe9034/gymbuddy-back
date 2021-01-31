package com.gymbuddy.backgymbuddy.admin.order.domain;

import com.gymbuddy.backgymbuddy.admin.base.Address;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "shipment")
@Data
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id")
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 15, nullable = false)
    private String phone;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus shipmentStatus;
}
