package com.gymbuddy.backgymbuddy.admin.cart.domain;

import com.gymbuddy.backgymbuddy.admin.goods.domain.Goods;
import com.gymbuddy.backgymbuddy.admin.program.domain.Program;
import com.gymbuddy.backgymbuddy.admin.user.domain.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cart")
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<Program> programs;

    @OneToMany(mappedBy = "cart")
    private List<Goods> goods;

    @Column
    private int count;

}
