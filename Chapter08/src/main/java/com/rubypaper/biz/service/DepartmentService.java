package com.rubypaper.biz.service;


import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.persistence.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("deptService")
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentRepository deptRepository;

    @Transactional
    public void insertDepartment(Department department) {
        deptRepository.insertDepartment(department);
    }

    public Department getDepartment(Department department) {
        return deptRepository.getDepartment(department);
    }
}
