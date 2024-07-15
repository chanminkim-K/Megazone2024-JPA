package com.rubypaper.biz.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Employee2Test")
public class Employee2 {
    @Id
    private Long id;

    private String name;

    private String mailId;

    @Column(name = "START_DATE") // 멤버 명과 S_EMP 테이블의 컬럼명이 다름
    // 매핑될 칼럼명을 명시적으로 매핑
    private Date startDate;

    private String title;

    @Column(name = "DEPT_NAME")
    private String deptName;

    private Double salary;

    @Column(name = "COMMISSION_PCT")
    private Double commissionPct;
}
