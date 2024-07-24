package com.rubypaper.biz.client;

import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.domain.EmployeeSalaryData;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JPQLBasicClient {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter06");

        try {
            dataInsert(emf);
            dataSelect(emf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            emf.close();
        }
    }

    private static void dataSelect(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT e FROM Employee e WHERE e.id = 1L";
        TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);

        // 1번 직원 검색
        Employee findEmp1 = query.getSingleResult();

        // 1번 직원 검색
        Employee findEmp2 = query.getSingleResult();

        if(findEmp1 == findEmp2){
            System.out.println("두 객체의 주소는 동일하다.");
        }

        em.close();
    }

    private static void dataInsert(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // 10 명의 직원 정보 등록
        for (int i = 1; i <= 10; i++) {
            Employee employee = new Employee();
            employee.setName("직원 " + i);
            employee.setMailId("anti-corona" + i);
            employee.setDeptName("개발부");
            employee.setSalary(12700.00 * i);
            employee.setStartDate(new Date());
            employee.setTitle("사원");
            employee.setCommissionPct(15.00);
            em.persist(employee);
        }

        tx.commit();
        em.close();
    }

}