package com.rubypaper.biz.persistence;

import com.rubypaper.biz.domain.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    @PersistenceContext
    private EntityManager em;

    public void insertEmployee(Employee employee) {
        System.out.println("===> JPA로 insertEmployee() 기능 처리");
        em.persist(employee);
    }

    public void updateEmployee(Employee employee) {
        System.out.println("===> JPA로 updateEmployee() 기능 처리");
        em.merge(employee);
    }

    public void deleteEmployee(Employee employee) {
        System.out.println("===> JPA로 deleteEmployee() 기능 처리");
        em.remove(em.find(Employee.class, employee.getId()));
    }

    public Employee getEmployee(Employee employee) {
        System.out.println("===> JPA로 getEmployee() 기능 처리");
        return (Employee)  em.find(Employee.class, employee.getId());
    }

    public List<Employee> getEmployeeList() {
        System.out.println("===> JPA로 getEmployeeList() 기능 처리");

        /**
         * 1. Join 이 동작하지 않는 경우
         */
        String jqpl1 = "SELECT e FROM Employee e";

        /**
         * 2. Join 이 동작하는 경우.
         *    - 묵시적 : select 절에 직원 객체 및 연관 관계의 부서 객체까지 포ㅓ함되도록 작성.
         *    - 명시적 : JOIN FETCH 를 명시적으로 작성.
         *              inner join, outer join 등
         */
        String jqpl2 = "FROM Employee emp JOIN FETCH emp.dept dept " +
                "ORDER BY emp.id DESC";

        return em.createQuery(jqpl2).getResultList();
    }

}
