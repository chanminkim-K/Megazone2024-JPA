package com.rubypaper.biz.repository;

import com.rubypaper.biz.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByName(String name);

    Page<Employee> findByNameContaining(String name, Pageable paging);

    List<Employee> findByNameContainingOrMailIdContaining(String name, String mailId);

    List<Employee> findByMailIdContainingOrderByNameDesc(String mailId);

    @Query("select emp.id, emp.name, emp.salary " +
            "from Employee emp " +
            "where emp.name like %:name% " +
            "order by emp.id desc")
    List<Object[]> findByJPQL(@Param("name") String name, Pageable paging);

    @Query(value = "select id, name, salary " +
            "from s_emp " +
            "where name like '%'||?1||'%' " +
            "order by id desc",
            nativeQuery = true)
    List<Object[]> findByNativeQuery(String name);
}
