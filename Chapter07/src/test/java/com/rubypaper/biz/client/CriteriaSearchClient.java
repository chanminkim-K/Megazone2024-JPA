package com.rubypaper.biz.client;

import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.domain.Employee;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CriteriaSearchClient {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter07");
        
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
        
        // 검색 정보 설정
        String searchCondition = "TITLE";
        String searchKeyword = "과장";

        // 크라이테리어 빌더 생성
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);

        // FROM Employee emp
        Root<Employee> emp = criteriaQuery.from(Employee.class);

        // SELECT emp
        criteriaQuery.select(emp);

        // JOIN FETCH emp.dept dept
        emp.fetch("dept", JoinType.LEFT);

        // WHERE (emp.mailId like 'Viru%'
        //        OR emp.salary >= 50000.00)
        //        AND emp.dept.name = '영업부)
        Predicate[] condition1 = {builder.like(emp.<String>get("mailId"), "Viru%"),
                                builder.ge(emp.<Double>get("salary"), 50000.00)};
        Predicate condition2 = builder.equal(emp.get("dept").get("name"), "영업부");

        Predicate predicate = builder.and(builder.or(condition1), condition2);
        criteriaQuery.where(predicate);


        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
        List<Employee> resultList = query.getResultList();
        for (Employee result : resultList) {
            System.out.println("---> " + result.toString());
        }

        em.close();
    }

    private static void dataInsert(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // 부서 정보 등록
        Department devDept = new Department();
        devDept.setName("개발부");
        em.persist(devDept);

        Department salesDept = new Department();
        salesDept.setName("영업부");
        em.persist(salesDept);

        for (int i = 1; i <= 3; i++) {
            Employee employee = new Employee();
            employee.setName("개발맨 " + i);
            employee.setMailId("Corona" + i);
//            employee.setDeptName("개발부");
            employee.setDept(devDept);
            employee.setSalary(12700.00 * i);
            employee.setStartDate(new Date());
            employee.setTitle("사원");
            employee.setCommissionPct(10.00);
            em.persist(employee);
        }

        for (int i = 1; i <= 3; i++) {
            Employee employee = new Employee();
            employee.setName("영업맨 " + i);
            employee.setMailId("Virus" + i);
//            employee.setDeptName("영업부");
            employee.setDept(salesDept);
            employee.setSalary(23800.00 * i);
            employee.setStartDate(new Date());
            employee.setTitle("과장");
            employee.setCommissionPct(15.00);
            em.persist(employee);
        }

        // 부서 정보가 없는 직원 등록
        Employee employee = new Employee();
        employee.setName("아르바이트");
        employee.setMailId("Alba-01");
//        employee.setDeptName("개발부");
        employee.setSalary(10000.00);
        em.persist(employee);

        tx.commit();
        em.close();
    }


}