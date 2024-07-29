package com.rubypaper.biz.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString(exclude = "dept")
@Entity
@Table(name = "S_EMP")
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /**
     * @OneToMany
     * - fetch 의 기본 속성값 : LAZY
     */

    @Column(name = "MAIL_ID")
    private String mailId;

    private Double salary;


    @ManyToOne
    @JoinColumn(name = "DEPT_ID")
    private Department dept;

    public void setDept(Department dept) {
        this.dept = dept;

        // Department 엔티티의 컬렉션에도 Employee 참조를 설정한다
        dept.getEmployeeList().add(this);
    }
}
