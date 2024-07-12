package com.rubypaper.persistence.hibernate;

import java.util.List;
import java.sql.Timestamp;

public class EmployeeServiceClient {
    public static void main(String[] args) {
        EmployeeVO vo = new EmployeeVO();
        vo.setId(5L);
        vo.setName("고길동");
        vo.setStartDate(new Timestamp(System.currentTimeMillis()));
        vo.setTitle("과장");
        vo.setDeptName("총무부");
        vo.setSalary(2500.00);
        vo.setEamil("quest@ruby.co.kr");

        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.insertEmployee(vo);

        List<EmployeeVO> employeeList = employeeDAO.getEmployeeList();
        for(EmployeeVO employee : employeeList) {
            System.out.println("---> " + employee.toString());
        }
    }

}