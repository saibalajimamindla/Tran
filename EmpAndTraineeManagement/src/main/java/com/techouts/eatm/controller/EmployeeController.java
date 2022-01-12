package com.techouts.eatm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techouts.eatm.converter.EmployeeConvertor;
import com.techouts.eatm.dao.EmployeeRepository;
import com.techouts.eatm.dto.EmployeeDto;
import com.techouts.eatm.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeDao;

	@Autowired
	EmployeeServiceImpl employeeService;

	@Autowired
	EmployeeConvertor employeeConvertor;

	@GetMapping("/")
	public EmployeeDto getEmployee() {
		return new EmployeeDto();
	}

	@PostMapping("/add")
	public EmployeeDto addEmployee(@RequestBody EmployeeDto empdto) {
		return employeeService.saveEmployee(empdto);
	}

	@DeleteMapping("/{id}")
	public String removeEmployee(@PathVariable Long id) {
		return employeeService.removeEmployee(id);
	}

	@GetMapping("/id/{id}")
	public EmployeeDto getEmployeeById(@PathVariable Long id) {
		return employeeService.getEmployeeById(id);
	}

	@GetMapping("/name/{name}")
	public EmployeeDto getEmployeeByName(@PathVariable String name) {
		return employeeService.getEmployeeByName(name);

	}

	@GetMapping("/all")
	public List<EmployeeDto> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/all/{track}")
	public List<EmployeeDto> getAllEmployees(@PathVariable String track) {
		return employeeService.getAllEmployeesByTrack(track);
	}
}
