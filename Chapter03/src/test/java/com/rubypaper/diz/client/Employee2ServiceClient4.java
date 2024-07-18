package com.rubypaper.diz.client;

import com.rubypaper.biz.domain.Employee2;
import jakarta.persistence.*;

/*
 * 엔터티 수정 실습
 * - 더티 체크 가 발생하도록 코드를 작성
 * - merge() 호출
 *
 */

public class Employee2ServiceClient4 {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("Chapter03");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // 직원 검색
            Employee2 findEmp = em.find(Employee2.class, 1L);

            // 직원 이름 변경
            tx.begin();
            findEmp.setName("이름수정");
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