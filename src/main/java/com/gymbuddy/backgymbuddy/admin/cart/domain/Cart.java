package com.gymbuddy.backgymbuddy.admin.cart.domain;

import com.gymbuddy.backgymbuddy.admin.user.domain.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long program_id;

    private Long goods_id;

    private int count;

}
