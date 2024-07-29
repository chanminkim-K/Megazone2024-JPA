package com.rubypaper.biz.service;


import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.Sort.*;

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

    public List<Object[]> getEmployeeList(Employee employee) {
        PageRequest paging = PageRequest.of(0, 3, Direction.DESC, "id");
        return empRepository.findByJPQL(employee.getName(), paging);
    }

}
