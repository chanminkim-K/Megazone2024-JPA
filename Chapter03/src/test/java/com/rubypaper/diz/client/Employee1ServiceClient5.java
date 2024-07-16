package com.rubypaper.diz.client;

import com.rubypaper.biz.domain.Employee1;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/*
 * 엔티티 분리 상태 -> 관리 상태 실습
 */

public class Employee1ServiceClient5 {

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



            // ****** 엔티티 분리 실습 시작 ****** //
            if(em.contains(employee)) {
                System.out.println("영속성 컨테이너에 등록된 상태");
            }

            // 엔티티 분리
            em.detach(employee);
            if(!em.contains(employee)) {
                System.out.println("영속성 컨테이너에 분리된 상태");
            }

            // ****** 엔티티 분리 -> 관리 실습 시작 ****** //
            em.merge(employee);

            if(em.contains(employee)) {
                System.out.println("영속성 컨테이너에 등록된 상태");
            }

            // merge() 동작 방식
            /**
             * 1차 캐쉬에서 해당 식별자가 있는지 확인.
             * 없으면, database 를 조회.
             *
             * database 에 값이 있으면, 객체 등록
             *
             * entitu 를 수정한 경우에 수정한 결과가 반영이 되지 않음.
             * DB에서 조회된 내용으로 덮어쓰기가 됨.
             *
             */


        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

}