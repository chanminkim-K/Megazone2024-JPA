package com.rubypaper.biz.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Date;

/*
 * JPA 관리한 Entity 생성
 * - Entity 를 이용해서 데이터 삽입하는 테스트
 *   1. EntityManagerFactory 이용해서 EntityManager를 생성
 *   2. EntityManager 를 이용해서 영속성 관리
 *      persist()
 */

class EmployeeServiceClient2 {

    public static void main(String[] args) {

        // <persistence-unit name="Chapter02"> 의 설정 정보를 참조
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("Chapter02");

        EntityManager em = emf.createEntityManager();

        // 엔터티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();

        try {
            // 영속성 관리 엔티티 생성
            Employee1 employee1 = new Employee1();

            employee1.setId(1L);
            employee1.setName("홍길동");
            employee1.setMailId("hong@naver.com");
            employee1.setStartDate(new Date());
            employee1.setTitle("대리");
            employee1.setDeptName("개발부");
            employee1.setSalary(2500.00);
            employee1.setCommissionPct(12.50);

            // 트랜잭션 시작
            tx.begin();

            // 연속성 관리를 위한 엔티티 묶음
            em.persist(employee1);

            // 트랜잭션 종료
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }


    }

}