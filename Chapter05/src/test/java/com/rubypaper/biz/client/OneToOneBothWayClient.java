package com.rubypaper.biz.client;

import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.domain.EmployeeCard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OneToOneBothWayClient {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("Chapter05");

        try {
            dataInsert(emf);
            dataSelect(emf);

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            emf.close();
        }

    }

    private static void dataInsert(EntityManagerFactory emf) throws ParseException {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // 사원증 등록
        EmployeeCard card = new EmployeeCard();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        card.setExpireDate(dateFormat.parse("2025-12-31"));
        card.setRole("MASTER");
        em.persist(card);

        // 직원 등록
        Employee employee = new Employee();
        employee.setName("메시");
        // 사원증에 대한 참조 설정
        employee.setEmployeeCard(card);
        em.persist(employee);

        tx.commit();
        em.close();

    }

    private static void dataSelect(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();

        Employee employee = em.find(Employee.class, 1L);
        System.out.println("직원을 통한 사원증 정보 접근 : " + employee.getCard().toString());


//        EmployeeCard employeeCard = em.find(EmployeeCard.class, 1L);
//        System.out.println("사원증을 통한 직원 정보 접근 = " + employeeCard.toString());

        // 검색된 사원증을 통해 직원 정보 사용하기
//        EmployeeCard employeeCard = em.find(EmployeeCard.class, 1L);
//        System.out.println("사원증 유효기간 : " + employeeCard.getExpireDate());
//        System.out.println("사원증 소유자 : " + employeeCard.getEmployee().getName());

        // 검색된 직원을 통해 사원증 정보 사용하기
//        Employee employee = em.find(Employee.class, 1L);
//        System.out.println("사원증 소유자 : " + employee.getName());
//        System.out.println("사원증 유효기간 : " + employee.getCard().getExpireDate());
    }
}