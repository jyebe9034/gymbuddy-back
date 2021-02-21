package com.gymbuddy.backgymbuddy.admin.cart.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gymbuddy.backgymbuddy.admin.order.domain.Orders;
import com.gymbuddy.backgymbuddy.admin.program.domain.Program;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "cart_program")
@Data
public class CartProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_program_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "program_id")
    private Program program;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column
    private BigDecimal programPrice;

    @Column
    private int count; // 주문 수량
}
