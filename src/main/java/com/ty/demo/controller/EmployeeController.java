package com.ty.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ty.demo.model.Employee;
import com.ty.demo.service.EmployeeServiceImpl;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	//display list of employee
	@GetMapping("/employee")
	public String viewHomePage(Model model) {
		return pagination(1, model);
//without pagination
//		model.addAttribute("listOfEmployess", employeeServiceImpl.getAllEmployees());
//		return "index";
	}
	
	//for adding
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		Employee employee=new Employee();
		model.addAttribute("employee",employee);
		return "add_employee";
	}
	
	@PostMapping("/saveemployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeServiceImpl.save(employee);
		return "redirect:/employee";
	}
	
	//SHowing update form 
	@GetMapping("/showUpdateEmployeeForm/{id}")
	public String showUpdateEmployeeForm(Model model,@PathVariable(value="id") long id) {
		Employee employee=employeeServiceImpl.getEmployeeById(id);
		model.addAttribute("employee",employee);
		return "update_employee";
	}
	
	 
	//getmapping becouse it is not a rest application it is a web application
	@GetMapping("deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value="id") long id) {
		employeeServiceImpl.delete(id);
		return "redirect:/employee";
	}
	
	@GetMapping("page/{pageNo}")
	public String pagination(@PathVariable(value="pageNo") int pageNo,Model model) {
		 int pageSize=5;
		 Page<Employee> page=employeeServiceImpl.findPaginate(pageNo, pageSize);
		 List<Employee> listEmployee=page.getContent();
		 model.addAttribute("currentPage", pageNo);
		 model.addAttribute("totalPages", page.getTotalPages());
		 model.addAttribute("totalItems", page.getTotalElements());//no of rows
		 model.addAttribute("listEmployee", listEmployee);
		return "index";
	}
	
	
}
