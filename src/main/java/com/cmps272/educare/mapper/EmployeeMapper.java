package com.cmps272.educare.mapper;

import com.cmps272.educare.dto.EmployeeDto;
import com.cmps272.educare.entity.Employee;

public class EmployeeMapper {

	public static EmployeeDto mapToEmployeeDto(Employee employee) {
		return new EmployeeDto(employee.getId(), employee.getName(), employee.getDepartment());
	}

	public static Employee mapToEmployee(EmployeeDto employeeDto) {
		return new Employee(employeeDto.getId(), employeeDto.getName(), employeeDto.getDepartment());
	}

}