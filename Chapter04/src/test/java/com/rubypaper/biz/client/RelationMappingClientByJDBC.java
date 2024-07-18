package com.rubypaper.biz.client;

import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.repository.EmployeeDAO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RelationMappingClientByJDBC {

    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        List<Employee> employeeList = employeeDAO.getEmployeeList();

        for (Employee employee : employeeList) {
            System.out.println(employee.getName() + "직원의 부서명 : " +
                    employee.getDept().getName());
        }
    }

}