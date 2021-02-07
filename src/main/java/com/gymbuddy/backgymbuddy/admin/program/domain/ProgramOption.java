package com.gymbuddy.backgymbuddy.admin.program.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "program_option")
@Data
public class ProgramOption extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_option_id")
    private Long id;

    /**
     * 클래스가 열리는 날짜 & 시간
     */
    @Column(length = 100, nullable = false)
    private String classDateTime;

    /**
     * 인원수
     */
    @Column(nullable = false)
    private int userCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private Program program;

}
