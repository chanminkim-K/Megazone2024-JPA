package com.rubypaper.biz.service;


import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.persistence.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("empService")
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository empRepository;

    public void insertEmployee(Employee employee) {
        empRepository.insertEmployee(employee);
    }

    public void updateEmployee(Employee employee) {
        empRepository.updateEmployee(employee);
    }

    public void deleteEmployee(Employee employee) {
        empRepository.deleteEmployee(employee);
    }

    public Employee getEmployee(Employee employee) {
        return empRepository.getEmployee(employee);
    }

    public List<Employee> getEmployeeList() {
        return empRepository.getEmployeeList();
    }
}
