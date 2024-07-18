package com.rubypaper.diz.client;

import com.rubypaper.biz.domain.Employee1;
import com.rubypaper.biz.domain.Employee2;
import jakarta.persistence.*;

/*
 * 엔티티 조회 : 영속성 컨테이너에 존재하지 않는 엔티티를 조회하는 실습
 */

public class Employee2ServiceClient {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("Chapter03");

        EntityManager em = emf.createEntityManager();

        // 커밋할 때만 flush 가 동작하게 됨
        em.setFlushMode(FlushModeType.COMMIT);

        // 엔터티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();

        try {
            // Entity 생성
            Employee2 employee = new Employee2();
            employee.setName("홍길동");

            // 트랜잭션 시작
            tx.begin();
            em.persist(employee);
            tx.commit(); // flush 동작
//            em.flush();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

}