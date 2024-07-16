package com.rubypaper.diz.client;

import com.rubypaper.biz.domain.Employee1;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/*
 * 엔티티 삭제 상태 실습
 */

public class Employee1ServiceClient4 {

    public static void main(String[] args) {
        // <persistence-unit name="Chapter03"> 의 설정 정보를 참조
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("Chapter03");

        EntityManager em = emf.createEntityManager();

        // 엔터티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();

        try {
            Employee1 employee = new Employee1();
            //employee.setId(1L);
            employee.setName("홍길동");

            // 영속성 컨테이너에 엔티티를 등록
            // DB에 저장. 트랜잭션 안에서 persist() 가 호출

            // 트랜잭션 시작
            tx.begin();
            // 영속성 관리를 위한 엔터티 등록
            em.persist(employee);
            // 트랜잭션 종료
            tx.commit();


            Employee1 findEmp = em.find(Employee1.class, 1L);
            System.out.println(findEmp.toString());

            // ****** 엔티티 삭제 실습 시작 ****** //

            tx.begin();

            em.remove(findEmp);

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

}