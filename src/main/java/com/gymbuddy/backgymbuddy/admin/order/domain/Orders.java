package com.gymbuddy.backgymbuddy.admin.order.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import com.gymbuddy.backgymbuddy.admin.enums.status.CsStatus;
import com.gymbuddy.backgymbuddy.admin.enums.status.ProgramStatus;
import com.gymbuddy.backgymbuddy.admin.enums.status.ShipmentStatus;
import com.gymbuddy.backgymbuddy.admin.user.domain.User;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Orders extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderProgram> orderPrograms = new ArrayList<>();

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderGoods> orderGoods = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private CsStatus csStatus;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus shipmentStatus;

    @Enumerated(EnumType.STRING)
    private ProgramStatus programStatus;

    @Column
    private BigDecimal totalPrice;
}
