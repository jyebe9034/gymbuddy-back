package com.gymbuddy.backgymbuddy.admin.order.domain;

import com.gymbuddy.backgymbuddy.admin.base.Address;
import com.gymbuddy.backgymbuddy.admin.user.domain.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Order {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime orderDate; // hibernate가 자동으로 현재시간을 지원해 줌.

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Address address;
}
