package com.gymbuddy.backgymbuddy.admin.cart.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Cart {

    @Id
    private Long id;
}
