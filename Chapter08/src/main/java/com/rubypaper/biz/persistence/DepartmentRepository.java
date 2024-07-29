package com.rubypaper.biz.persistence;


import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.domain.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepository {

    @PersistenceContext
    private EntityManager em;

    public void insertDepartment(Department department) {
        System.out.println("===> JPA로 insertDepartment() 기능 처리");
        em.persist(department);
    }


    public Department getDepartment(Department department) {
        System.out.println("===> JPA로 getDepartment() 기능 처리");
        return em.find(Department.class, department.getDeptId());
    }

}
