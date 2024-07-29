package com.rubypaper.biz.repository;

import com.rubypaper.biz.domain.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
