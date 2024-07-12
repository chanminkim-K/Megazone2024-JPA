package com.rubypaper.persistence.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class EmployeeDAO {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public EmployeeDAO() {
        String config = "hibernate-mapper/hibernate.cfg.xml";
        sessionFactory =
                new Configuration().configure(config).buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.getTransaction();
    }

    public void insertEmployee(EmployeeVO vo){
        System.out.println("===> Hibernate 기반으로 직원 등록 기능 처리");
        try{
            transaction.begin();
            session.persist(vo);

            /**
             * 하이버네이트에서의 엔터티 관리 상태
             *
             * - 분리 상태
             * - 관리(영속) 상태
             * - 비영속 상태
             * - 삭제된 상태
             *
             * 회원 관리 엔티티 기준으로 상태 구분
             * - 비영속 상태
             *   Member member = new Member(1L, "홍길동");
             * - 관리 상태
             *   em.persist(member);
             * - 분리 상태
             *   em.detach(member);
             * - 삭제 상태
             *   em.remove(member);
             */
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        }
    }

    public List<EmployeeVO> getEmployeeList() {
        System.out.println("===> Hibernate 기반으로 직원 목록 조회 기능 처리");
        String jpql = "SELECT e FROM EmployeeVO e ORDER BY e.id";
        /***
         * JPQL( Java Persistence Query Language )
         *
         * - 기존 SQL과의 차이점
         *   SQL : 대상이 데이터베이스의 테이블의 데이터
         *   JPQL : 영속 컨테이너(하이버네이트)에서 관리되고 있는 엔터티가 대상
         *
         * - 비슷한 점
         *   기존의 SQL 문법구조와 비슷
         */
        List<EmployeeVO> employeeList = session.createQuery(jpql).getResultList();
        return employeeList;
    }
}
