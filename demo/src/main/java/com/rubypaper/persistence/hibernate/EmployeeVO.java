package com.rubypaper.persistence.hibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity // 하이버네이트에서 관리하는 엔티티 클래스. H2의 테이블과 연결
@Table(name = "S_EMP") // EmployeeVO 는 S_EMP 테이블과 매핑
public class EmployeeVO {

    @Id
    private Long id;

    private String name;

    @Column(name = "START_DATE") // 멤버 명과 S_EMP 테이블의 컬럼명이 다름
                                 // 매핑될 칼럼명을 명시적으로 매핑
    private Timestamp startDate;

    private String title;

    @Column(name = "DEPT_NAME")
    private String deptName;

    private Double salary;

    private String eamil;
}
