package com.rubypaper.biz.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * @Table 에 uniqueConstraint 속성 추가
 * NAME, MAILID 두 컬럼을 대상으로 unique 제약조건 생성.
 *
 * 1. 테이블에 uniqueConstraint 생성 유무 확인)
 *    NAME, MAILID 의 두 개의 컬럼으로 복합 unique 제약조건이 생성되었는지 확인
 * 2. uniqueConstraint 동작 테스트
 * 3. 테스트 데이터 생성
 *    홍길동, hong@naver.com
 *
 * 그래서, 정말 중요한 것은 생성되는 데이터가 처음부터 깨끗하게 만들어지지
 * 읺는다면, 향후에 개발중에서 이동한 데이터가 나오게 됨
 * ( 들어가는 것이 깨끗해야 나오는 것도 깨끗하다. )
 */
@Data
@Entity
@Table(name = "Employee3",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME", "MAILID"})})
public class Employee3 {
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
