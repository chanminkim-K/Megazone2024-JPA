package com.rubypaper.diz.client;

import com.rubypaper.biz.domain.Employee2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/*
 * p.188
 * 분리상태에서의 엔티티 수정
 *
 * - 엔티티 생성 및 등록
 * - 분리 : em.clear()
 * - 엔티티 수정
 */

public class Employee2ServiceClient5 {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("Chapter03");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            Employee2 employee = new Employee2();
            employee.setName("홍길동");

            tx.begin();
            em.persist(employee);

            /**
             * 묵시적 flush 실행 => insert 생성됨.
             *
             * persistence.xml 의 테이블이 생성되는 동안
             * DB의 테이블에도 없는 데이터임
             * 영속성 컨테이너에 없는 엔티티임
             */
            tx.commit();

            // 엔티티 분리
            em.clear();


            // 준영속 엔티티 수정
            tx.begin();
            employee.setName("이름 수정");
            tx.commit();

            // 분리된 Entity를 수정하려면 어떻게...

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }

}