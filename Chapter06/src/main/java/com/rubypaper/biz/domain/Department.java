package com.rubypaper.biz.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "S_DEPT")
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;

    private String name;

    @OneToMany(mappedBy = "dept", cascade = CascadeType.PERSIST)
    private List<Employee> employeeList = new ArrayList<Employee>();
}
