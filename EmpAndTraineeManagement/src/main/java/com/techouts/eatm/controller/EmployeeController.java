package com.techouts.eatm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techouts.eatm.converter.EmployeeConvertor;
import com.techouts.eatm.dao.EmployeeRepository;
import com.techouts.eatm.dao.EmployeeTechnologyRatingRepository;
import com.techouts.eatm.dto.EmployeeDetailsDto;
import com.techouts.eatm.dto.EmployeeDto;
import com.techouts.eatm.dto.EmployeeTechnologyRatingDto;
import com.techouts.eatm.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeDao;

	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeConvertor employeeConvertor;
	
	@Autowired
	EmployeeTechnologyRatingRepository employeeTechnologyRatingDao;

	@GetMapping("/")
	public EmployeeDto getEmployee() {
		return new EmployeeDto();
	}

	@PostMapping("/add")
	public EmployeeDto addEmployee(@RequestBody EmployeeDto empdto) {
		return employeeService.saveEmployee(empdto);
	}

	@DeleteMapping("/delete/{id}")
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
	
	@GetMapping("/Rating/{empid}")
	public EmployeeDetailsDto employeeTechnologyRating(@PathVariable Long empid)
	{
		return employeeService.empTechRating(empid);
	}
	
	@GetMapping("/techrating/{empid}/{techid}")
	public EmployeeTechnologyRatingDto techrating(@PathVariable long empid,@PathVariable long techid)
	{
		return employeeService.technologyRating(empid,techid);
	}
	
	@PostMapping("/techrating/{empid}/{techid}")
	public String setTechrating(@PathVariable long empid,@PathVariable long techid,@RequestParam int rating)
	{
	      
	    	return employeeService.updateRating( empid, techid, rating);
	    		 
	}
	
}
