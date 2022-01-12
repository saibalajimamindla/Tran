package com.techouts.eatm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.techouts.eatm.converter.EmployeeConvertor;
import com.techouts.eatm.dao.EmployeeDao;
import com.techouts.eatm.dto.EmployeeDto;
import com.techouts.eatm.model.Employee;
import com.techouts.eatm.service.EmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeDao employeeDao;

	Employee employee;
	@Autowired
	EmployeeService employeeService;

	@Autowired
	EmployeeConvertor employeeConvertor;

	final ModelAndView andView = new ModelAndView();

	/*
	 * @GetMapping("/add") public EmployeeDto getDto() { ModelAndView modelAndView =
	 * new ModelAndView("AddEmployee"); System.out.println("reached"); return new
	 * EmployeeDto(); }
	 */
	@GetMapping("/test")
	public ModelAndView test() {

		//ModelAndView modelAndView = new ModelAndView("AddEmployee");
		andView.addObject("addEmployee", new EmployeeDto());
		andView.setViewName("AddEmployee");
		// EmployeeDto dto=new EmployeeDto(); model.addAttribute("registration", dto);

		return andView;
	}

	@PostMapping("/register")
	// @ResponseBody
	public ModelAndView addEmployee(@ModelAttribute("addEmployee") EmployeeDto empdto) {
System.out.println(empdto.getDateOfJoining());
		EmployeeDto dto = employeeService.saveEmployee(empdto);
		// ModelAndView andView = new ModelAndView();
		andView.addObject("add", dto);
		System.out.println("thanks");
		andView.setViewName("success");
		return andView;
	}

	@DeleteMapping("/remove/{id}")
	public ModelAndView removeEmployee(@PathVariable Long id) {
		employeeService.removeEmployee(id);
        System.out.println(id);
		ModelAndView andView = new ModelAndView("deleteEmployee");

		return andView;
	}

	@GetMapping("/update/{empName}")
	public ModelAndView updateEmployees(@PathVariable String empName) {
		EmployeeDto dto = employeeService.getEmployeeByName(empName);
		ModelAndView andView = new ModelAndView();
	    andView.addObject("updateEmployee", dto);
		andView.setViewName("updateForm");
		return andView;
	}
	
	@PostMapping("/update")
	public ModelAndView updateEmployee(@ModelAttribute("updateEmployee") EmployeeDto empdto) {
		EmployeeDto dto=employeeService.saveEmployee(empdto);
		andView.addObject("add", dto);
		andView.setViewName("success");
		return andView;

	}

	@GetMapping("/{id}")
	public EmployeeDto getEmployees(@PathVariable Long id) {
		return employeeService.getEmployeeById(id);
	}

	@GetMapping("/{name}")
	public EmployeeDto getEmployees(@PathVariable String name) {
		return employeeService.getEmployeeByName(name);

	}

	@GetMapping("/allEmployeeDetail")
	public ModelAndView getAllEmployees() {
		
		List<EmployeeDto> dto=employeeService.getAllEmployees();
		andView.addObject("command", dto);
		andView.setViewName("allEmpDetails");
		return andView;
	}

	@GetMapping("/all/{track}")
	public List<EmployeeDto> getAllEmployees(@PathVariable String track) {
		return employeeService.getAllEmployeesByTrack(track);
	}
}
