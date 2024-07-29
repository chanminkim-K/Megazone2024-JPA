package com.rubypaper.biz.client;

import com.rubypaper.biz.config.SpringConfiguration;
import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.service.DepartmentService;
import com.rubypaper.biz.service.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;

class QueryMethodClient {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        DepartmentService deptService = (DepartmentService) container.getBean("deptService");
        EmployeeService employeeService = (EmployeeService) container.getBean("empService");

//        dataInsert(deptService, employeeService);
        dataSelect(employeeService);
    }

//    private static void dataSelect(EmployeeService employeeService) {
//        List<Employee> employeeList = employeeService.getEmployeeList();
//
//        System.out.println("직원 목록");
//        for (Employee employee : employeeList) {
//            System.out.println("---> " + employee.getName() + "의 부서명 : " + employee.getDept().getName());
//        }
//    }

    private static void dataSelect(EmployeeService employeeService) {
        Employee employee = new Employee();
        employee.setName("개발");
        employee.setMailId("Dev");
        List<Object[]> reulstList = employeeService.getEmployeeList(employee);

        System.out.println("검색된 직원 목록");
        for (Object[] result : reulstList) {
            System.out.println("--->" + Arrays.toString(result));
        }
    }
    private static void dataInsert(DepartmentService deptService, EmployeeService employeeService) {
        Department department1 = new Department();
        department1.setName("개발부");
        deptService.insertDepartment(department1);

        Department department2 = new Department();
        department2.setName("영업부");
        deptService.insertDepartment(department2);

        for (int i = 1; i <= 5; i++) {
            Employee employee = new Employee();
            employee.setName("개발직원 " + i);
            employee.setSalary(i * 12700.00);
            employee.setMailId("Dev-" + i);
            employee.setDept(department1);
            employeeService.insertEmployee(employee);
        }

        for (int i = 1; i <= 7; i++) {
            Employee employee = new Employee();
            employee.setName("영업직원 " + i);
            employee.setSalary(i * 24300.00);
            employee.setMailId("Sales-" + i);
            employee.setDept(department2);
            employeeService.insertEmployee(employee);
        }
    }

}