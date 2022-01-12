package com.techouts.eatm.service;

import java.util.List;

import com.techouts.eatm.dto.EmployeeDto;

public interface EmployeeService {
	public EmployeeDto saveEmployee(EmployeeDto dto);

	public String removeEmployee(Long id);

	public EmployeeDto getEmployeeById(Long id);

	public EmployeeDto getEmployeeByName(String name);

	public List<EmployeeDto> getAllEmployees();

	public List<EmployeeDto> getAllEmployeesByTrack(String track);
}
