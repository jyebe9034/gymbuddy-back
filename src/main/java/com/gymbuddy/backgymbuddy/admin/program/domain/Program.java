package com.gymbuddy.backgymbuddy.admin.program.domain;

import com.gymbuddy.backgymbuddy.admin.base.Product;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
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
    @Column
    private String programName;

    /**
     * 강사 이름
     */
    @Column
    private String coachName;

    /**
     * 클래스가 열리는 장소
     */
    @Column
    private String classAddress;

    /**
     * 클래스가 열리는 날짜
     */
    @Column
    private LocalDateTime classDate;

    /**
     * 클래스가 열리는 시간
     */
    @Column
    private LocalDateTime classTime;

    /**
     * 내용
     */
    @Column
    private String contents;

}
