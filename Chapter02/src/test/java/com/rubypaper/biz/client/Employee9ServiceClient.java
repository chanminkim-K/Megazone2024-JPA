package com.rubypaper.biz.client;

import com.rubypaper.biz.domain.Employee9;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/*
 * 식별자 Sequence 의 값 생성 순서
 */

public class Employee9ServiceClient {

    public static void main(String[] args) {
        // <persistence-unit name="Chapter02"> 의 설정 정보를 참조
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("Chapter02");

        EntityManager em = emf.createEntityManager();

        // 엔터티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();

        try {
            Employee9 employee = new Employee9();
            //employee.setId(1L);
            employee.setName("홍길동");

            // 트랜잭션 시작
            tx.begin();

            // 영속성 관리를 위한 엔터티 등록
            em.persist(employee);

            // 클라이언트가 persist() 를 호출
            // id null 이 설정된 insert 문을
            // 데이터베이스에 전송
            System.out.println("등록 전 Id : " + employee.getId());

            // 트랜잭션 커밋하기 까지 30초 라는 시간동안
            // select 한 결과를 확인
            /*
            for(int i = 0; i < 30; i++){
                Thread.sleep(1000);
                System.out.println("sleep...." + i + " 초 경과중....");
            }
             */
            // 데이터베이스가 id 컬럼에 증가된 식별자 값을 할당

            // 클라이언트 쪽에도 JPA가 관리하는 Employee 객체에도
            // 증가된 식별자 값이 할당
            System.out.println("등록 후 Id : " + employee.getId());

            // 트랜잭션 종료
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