package com.gymbuddy.backgymbuddy.admin.program.domain;

import com.gymbuddy.backgymbuddy.admin.base.Product;
import com.gymbuddy.backgymbuddy.admin.cart.domain.Cart;
import com.gymbuddy.backgymbuddy.admin.cart.domain.CartProgram;
import com.gymbuddy.backgymbuddy.admin.order.domain.OrderProgram;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "program")
@Data
public class Program extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Long id;

    /**
     * 프로그램 이름
     */
    @Column(length = 100, nullable = false)
    private String title;

    /**
     * 강사 이름
     */
    @Column(length = 30, nullable = false)
    private String coach;

    /**
     * 클래스가 열리는 장소
     */
    @Column(length = 200, nullable = false)
    private String classAddress;

    /**
     * 클래스가 열리는 날짜
     */
    @Column(length = 100, nullable = false)
    private String classDate;

    /**
     * 클래스가 열리는 시간
     */
    @Column(length = 100, nullable = false)
    private String classTime;

    /**
     * 수강료
     */
    @Column(nullable = false)
    private BigDecimal price;

    /**
     * 메인 노출 여부
     */
    @Column
    private String mainYn;

//    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
//    private List<OrderProgram> orderProduct = new ArrayList<>();
//
//    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
//    private List<CartProgram> cartProgram = new ArrayList<>();

//    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
//    private List<ProgramOption> programOptions = new ArrayList<>();
}
