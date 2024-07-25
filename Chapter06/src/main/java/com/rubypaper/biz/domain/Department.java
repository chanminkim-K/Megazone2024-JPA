package com.rubypaper.biz.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "employeeList")
@Entity
@Table(name = "S_DEPT")
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPT_ID")
    private Long deptId;

    private String name;

    @OrderColumn(name = "EMP_IDX")
    @OneToMany(mappedBy = "dept", cascade = CascadeType.PERSIST)
    private List<Employee> employeeList = new ArrayList<Employee>();
}
