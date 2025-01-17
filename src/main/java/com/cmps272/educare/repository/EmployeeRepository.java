package com.cmps272.educare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cmps272.educare.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}