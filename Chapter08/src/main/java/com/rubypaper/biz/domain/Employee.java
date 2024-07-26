package com.rubypaper.biz.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "S_EMP")
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "MAIL_ID")
    private String mailId;

    @Column(name = "STRAT_DATE")
    private Date startDate;

    private String title;

    // Department 객체와 관계를 맺으면서 사용하지 않음
//    @Column(name = "DEPT_NAME")
//    private String deptName;

    private Double salary;


    @Column(name = "COMMISSION_PCT")
    private Double commissionPct;

    @ManyToOne
    @JoinColumn(name = "DEPT_ID")
    private Department dept;

    public void setDept(Department dept) {
        this.dept = dept;

        // Department 엔티티의 컬렉션에도 Employee 참조를 설정한다
        dept.getEmployeeList().add(this);
    }
}
