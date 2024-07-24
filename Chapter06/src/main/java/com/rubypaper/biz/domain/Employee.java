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

    @Column(name = "DEPT_NAME")
    private String deptName;

    private Double salary;

    @Column(name = "COMMISSION_PCT")
    private Double commissionPct;

    @ManyToOne
    @JoinColumn(name = "DEPT_ID")
    private Department dept;

    public void setDept(Department dept) {
        this.dept = dept;
        if(dept != null)
            dept.getEmployeeList().add(this);
    }
}
