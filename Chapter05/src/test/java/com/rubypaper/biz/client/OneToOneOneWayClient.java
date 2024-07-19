package com.rubypaper.biz.client;

import com.rubypaper.biz.domain.Employee;
import com.rubypaper.biz.domain.EmployeeCard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OneToOneOneWayClient {

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

        // 직원 등록
        tx.begin();

        Employee employee = new Employee();
        employee.setName("메시");
        em.persist(employee);

        // 사원증 등록
        EmployeeCard card = new EmployeeCard();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        card.setExpireDate(dateFormat.parse("2025-12-31"));
        card.setRole("MASTER");

        // 직원에 대한 참조 설정
        card.setEmployee(employee);
        em.persist(card);

        tx.commit();
        em.close();
    }

    private static void dataSelect(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();

        EmployeeCard employeeCard = em.find(EmployeeCard.class, 1L);
        System.out.println("검색된 사원증 번호 : " + employeeCard.getCardId());
        System.out.println("권한 : " + employeeCard.getRole());
        System.out.println("사원증 소유자 : " + employeeCard.getEmployee().getName());
    }
}