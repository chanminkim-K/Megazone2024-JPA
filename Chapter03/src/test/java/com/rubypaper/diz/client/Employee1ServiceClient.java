package com.rubypaper.diz.client;

import com.rubypaper.biz.domain.Employee1;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/*
 * 본 테스트 케이스를 실행하면 오류가 발생함.
 *
 * 현재 사용중이 H2 최신 버전에 버그가 있음.
 *
 * - 오류 해결 방법
 *   1. h2 1.4.200 버전을 사용하면 오류가 발생하지 않음.
 *   2. persistence.xml 에서 jdbc url 정보를 수정
 *      2.1 변경 전
 *          jdbc:h2:tcp://localhost/./test
 *      2.2 변경 후
 *          jdbc:h2:tcp://localhost/./test;MODE=MySQL
 *   3. strategy 변경
 *      3.1 변경 전
 *          GenerationType.IDENTITY
 *      3.2 변경 후
 *          GenerationType.SEQUENCE
 */

public class Employee1ServiceClient {

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

            // Entity 수정
            tx.begin();
            // 결과적으로 update 문장이 h2 db에 전송.
            /*
             * Dirty Cheeking
             * 영속성 컨테이너는 관리 중인 엔티티의 변경이 되는 순간,
             * 변경을 감지하여 데이터베이스에 update 문장을 전송
             *
             */
            employee.setName("이름 수정1");
            // 트랜잭션 종료
            tx.commit();

            // DB 에 변경 사항을 반영하려면,
            // 트랜잭션 내부에서 수행을 해야함.
            employee.setName("이름 수정2");

            // ************** 영속성 컨테이너 등록, Entity Manager 의 find() ************** //
            Employee1 findEmp = em.find(Employee1.class, 1L);
            System.out.println(findEmp.toString());

            /**
             * 1. find() 메소드를 사용한다고 해서 항상 DB에서 조회하는건 아님.
             *      영속성 컨테이너에서 먼저 조회 후, 없으면 DB 에서 조회.
             * 2. 조회된 사원정보의 이름과 DB의 사원정보의 이름이 다름
             *      Entity 는 컨테이너에서 관리되고 있지만,
             *      변경사항이 DB로 전송되지 않은 상태.
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