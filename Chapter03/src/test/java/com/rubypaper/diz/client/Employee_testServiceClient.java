package com.rubypaper.diz.client;

import com.rubypaper.biz.domain.Employee_test;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Date;

/*
 * JPA 관리한 Entity 생성
 * - Entity 를 이용해서 데이터 삽입하는 테스트
 *   1. EntityManagerFactory 이용해서 EntityManager를 생성
 *   2. EntityManager 를 이용해서 영속성 관리
 *      persist()
 */

class Employee_testServiceClient {

    public static void main(String[] args) {

        // <persistence-unit name="Chapter02"> 의 설정 정보를 참조
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("Chapter03");

        EntityManager em = emf.createEntityManager();

        try {
            // 영속성 관리 엔티티 생성
            Employee_test employeeTest = new Employee_test();

            employeeTest.setId(1L);
            employeeTest.setName("홍길동");
            employeeTest.setMailId("hong@naver.com");
            employeeTest.setStartDate(new Date());
            employeeTest.setTitle("대리");
            employeeTest.setDeptName("개발부");
            employeeTest.setSalary(2500.00);
            employeeTest.setCommissionPct(12.50);

            // 연속성 관리를 위한 엔티티 묶음
            em.persist(employeeTest);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }


    }

}