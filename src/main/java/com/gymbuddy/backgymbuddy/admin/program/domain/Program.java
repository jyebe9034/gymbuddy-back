package com.gymbuddy.backgymbuddy.admin.program.domain;

import com.gymbuddy.backgymbuddy.admin.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Program extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Long id;

    /**
     * 프로그램 이름
     */
    private String programName;

    /**
     * 강사 이름
     */
    private String coachName;

    /**
     * 클래스가 열리는 장소
     */
    private String classAddress;

    /**
     * 클래스가 열리는 날짜
     */
    private String classDate;

    /**
     * 클래스가 열리는 시간
     */
    private String classTime;

    /**
     * 내용
     */
    private String contents;

    /**
     * 이미지 경로
     */
    private String imgPath;

    /**
     * 이미지 이름
     */
    private String imgName;

}
