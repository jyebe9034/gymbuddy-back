package com.gymbuddy.backgymbuddy.admin.program.domain;

import com.gymbuddy.backgymbuddy.admin.base.Product;
import com.gymbuddy.backgymbuddy.admin.cart.domain.Cart;
import com.gymbuddy.backgymbuddy.admin.order.domain.OrderProduct;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "program")
@Data
@DiscriminatorValue("PROGRAM")
public class Program extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Long id;

    /**
     * 프로그램 이름
     */
    @Column(length = 100, nullable = false)
    private String programName;

    /**
     * 강사 이름
     */
    @Column(length = 30, nullable = false)
    private String coachName;

    /**
     * 클래스가 열리는 장소
     */
    @Column(length = 200, nullable = false)
    private String classAddress;

    /**
     * 클래스가 열리는 날짜
     */
    @Column(length = 30, nullable = false)
    private String classDate;

    /**
     * 클래스가 열리는 시간
     */
    @Column(length = 10, nullable = false)
    private String classTime;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProduct = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
