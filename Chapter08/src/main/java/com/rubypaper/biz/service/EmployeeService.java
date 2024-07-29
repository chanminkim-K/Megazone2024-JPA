package com.rubypaper.biz.service;


import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.repository.EmployeeRepository;
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
        empRepository.save(employee);
    }

    public void updateEmployee(Employee employee) {
        empRepository.save(employee);
    }

    public void deleteEmployee(Employee employee) {
        empRepository.delete(employee);
    }

    public Employee getEmployee(Employee employee) {
        return empRepository.findById(employee.getId()).get();
    }

    public List<Employee> getEmployeeList() {
        return (List<Employee>)empRepository.findAll();
    }
}
