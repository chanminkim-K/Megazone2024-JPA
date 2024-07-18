package com.rubypaper.biz.client;

import com.rubypaper.biz.domain.Department;
import com.rubypaper.biz.domain.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ManyToOneWayClient {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("Chapter04");

        try {
            // 부서 등록, 사원 등록
//            dataInsert(emf);
//            dataSelect(emf);
            dataupdate(emf);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            emf.close();
        }



    }

    private static void dataSelect(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        Employee employee = em.find(Employee.class, 1L);

        /*
         * ManyToOne 관계에서 조회를 하는 경우
         *
         * 1. 기본
         *    outer join
         * 2. optional = false
         *    inner join => index join(성능이 좋음)
         * 3. fetch = FetchType.LAZY
         *    3.1 테이블 하나(S_EMP)로만 조회
         *        employee의 멤버만 사용 => S_EMP 만 조회하면 가능
         *    3.2 두 테이블(S_EMP, S_DEPT) 모두 조회
         *    	  employee, department 멤버 모두 사용
         *         => S_EMP, S_DEPT 두 테이블 모두 사용해야만 조회 가능
         * 4. fetch = FetchType.EAGER
         *    기본적으로 두 테이블(S_EMP, S_DEPT) 모두 사용.
         *    즉, employee의 멤버만 사용해도 S_EMP, S_DEPT 두 테이블 모두 사용.
         *
         * 성능을 고려한다면,
         * 2 > 3 순으로 선택하고, 4번과 1번은 경우에 따라서 선택해서 사용하면 됨.
         *
         */



        /*
         * 결과 : S_EMP table 만 조회가 됨.
         *
         * emp.getName() 는  employee 만 해당.
         */
        System.out.println(employee.getName());

        /*
         * 결과 : S_EMP, S_DEPT 가 모두 조회가 됨.
         *
         * employee가 필요하지만, department 타입의 멤버까지 사용.
         * department 정보를 출력하기 위해서는 S_DEPT 테이블까지 필요.
         */
        //System.out.println(employee.toString());

        /*
         * 실행 결과는 left outer join 임.
         *
         * 현재의 도메인은 사원관리임.
         * - 신규 사원인 경우에 교육인 끝나기 전까지는 부서배치를 보유
         *   => 해당 사원의 경우 부서정보가 없을 수 있음.
         *
         * - 근무중에 심각한 문제를 일으킨 경우
         *   => 부서에서 제외하고, 향후에 부서를 재배치.
         *      그래서, 부서정보가 없음.
         *
         * 따라서, 기본적으로 전체 사원정보가 조회되도록 하도록 하면,
         * outer join 이 필요하게 됨.
         *
         * 그리고, 필요에 따라서 inner join 이 되도록 설정해서 사용하면 됨.
         *
         */
    }

    private static void dataupdate(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        // 사원의 부서 이동 시나리오
        // 신규 부서 등록
        Department department = new Department();
        department.setName("기술영업");
        em.persist(department);

        // 사원의 부서 이동(변경)
        Employee employee = em.find(Employee.class, 1L);
        // 현재 사원번호 1번의 사원은 개발부 소속임.
        employee.setDept(department);
        tx.commit();
    }

    private static void dataInsert(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        EntityTransaction ex = em.getTransaction();

        ex.begin();

        // 부서 객체 생성
        Department department = new Department();
        department.setName("개발부");
        em.persist(department); // 영속성 컨테이너에 엔티티 등록

        // 사원 객체 생성
        Employee employee1 = new Employee();
        employee1.setName("둘리");
        employee1.setDept(department);
        em.persist(employee1);

        // 사원 객체 생성
        Employee employee2 = new Employee();
        employee2.setName("도우너");
        employee2.setDept(department);
        em.persist(employee2);

        ex.commit();
        em.close();
    }
}
