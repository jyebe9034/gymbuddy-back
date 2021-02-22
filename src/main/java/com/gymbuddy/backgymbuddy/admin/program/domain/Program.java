package com.gymbuddy.backgymbuddy.admin.program.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.gymbuddy.backgymbuddy.admin.base.Product;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "program")
@ToString(exclude = "program")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
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
    @Column(nullable = false)
    private String mainYn;

//    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
//    private List<OrderProgram> orderProduct = new ArrayList<>();
//
//    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
//    private List<CartProgram> cartProgram = new ArrayList<>();

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<ProgramOption> programOptions = new ArrayList<>();
}
