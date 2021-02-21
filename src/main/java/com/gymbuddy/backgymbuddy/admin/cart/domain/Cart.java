package com.gymbuddy.backgymbuddy.admin.cart.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import com.gymbuddy.backgymbuddy.admin.goods.domain.Goods;
import com.gymbuddy.backgymbuddy.admin.program.domain.Program;
import com.gymbuddy.backgymbuddy.admin.user.domain.User;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cart")
@Data
public class Cart extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartProgram> programs;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartGoods> goods;

    /**
     * 수량 or 인원수
     * 프로그램에서는 1로 고정
     */
    @Column
    private int count;

    @Column
    private BigDecimal totalPrice;

}
