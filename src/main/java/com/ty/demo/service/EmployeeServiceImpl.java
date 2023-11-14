package com.ty.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ty.demo.model.Employee;
import com.ty.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return  employeeRepository.findAll();
	}

	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		this.employeeRepository.save(employee);
	}

	public void update(Employee employee, long id) {
		 Employee rcvEmployee=employeeRepository.findById(id).get();
		 if(rcvEmployee!=null) {
			  employeeRepository.save(employee);
		 }else {
			 throw new UsernameNotFoundException("Employee not found");
		 }
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		 Employee rcvEmployee=employeeRepository.findById(id).get();
		 if(rcvEmployee!=null) {
			   employeeRepository.delete(rcvEmployee);
		 }else {
			 throw new UsernameNotFoundException("Employee not found");
		 }
		
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional=employeeRepository.findById(id);
		Employee employee=null;
		if(optional.isPresent()) {
			employee=optional.get();
		}else {
			throw new RuntimeException(id+" this Employee id is not present");
		}
		return employee;
	}

	@Override
	public Page<Employee> findPaginate(int pageNo, int pageSize) {
		Pageable pageable=PageRequest.of(pageNo-1, pageSize);
		return this.employeeRepository.findAll(pageable);
	}

}
