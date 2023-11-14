package com.ty.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ty.demo.model.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	
	void save(Employee employee);
	
	void delete(long id);
	
	Employee getEmployeeById(long id);
	
	Page<Employee> findPaginate(int pageNo,int pageSize);

}
