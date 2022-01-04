package com.techouts.eatm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techouts.eatm.converter.EmployeeConvertor;
import com.techouts.eatm.dao.EmployeeDao;
import com.techouts.eatm.dto.EmployeeDto;
import com.techouts.eatm.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeDao  employeeDao;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeConvertor employeeConvertor;

	@GetMapping("/add")
	public EmployeeDto getDto() {
		return new EmployeeDto();
	}

	@PostMapping("/add")
	@ResponseBody
	public EmployeeDto addEmployee(@ModelAttribute EmployeeDto empdto){

		return employeeService.saveEmployee(empdto);
	}

	@ResponseBody
	@DeleteMapping("/{id}")
	public String removeEmployee(@PathVariable Long id) {
		return employeeService.removeEmployee(id);
	}

	@GetMapping("/update/{name}")
	public EmployeeDto updateEmployees(@PathVariable String name) {
		return employeeService.getEmployeeByName(name);

	}

	@PutMapping("/update")
	public EmployeeDto updateEmployee(@ModelAttribute EmployeeDto empdto) {
		return employeeService.saveEmployee(empdto);

	}

	@GetMapping("/get/{id}")
	public EmployeeDto getEmployees(@PathVariable Long id) {
	return 	employeeService.getEmployeeById(id);
	}

	@GetMapping("/get/{name}")
	public EmployeeDto getEmployees(@PathVariable String name)  {
		return employeeService.getEmployeeByName(name);

	}

	@GetMapping("/all")
	public List<EmployeeDto> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/all/{track}")
	public List<EmployeeDto> getAllEmployees(@PathVariable String track)  {
		return employeeService.getAllEmployeesByTrack(track);
	}
}
