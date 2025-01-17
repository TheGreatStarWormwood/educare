package com.cmps272.educare.service;

import java.util.List;

import com.cmps272.educare.dto.EmployeeDto;
import com.cmps272.educare.exception.EmployeeNotFoundException;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long employeeId) throws EmployeeNotFoundException;
	List<EmployeeDto> getEmployees();
    void deleteEmployee(Long employeeId) throws EmployeeNotFoundException;
	EmployeeDto updateEmployee(EmployeeDto employeeDto) throws EmployeeNotFoundException;

}