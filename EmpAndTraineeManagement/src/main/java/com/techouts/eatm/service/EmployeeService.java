package com.techouts.eatm.service;

import java.util.List;

import com.techouts.eatm.dto.EmployeeDetailsDto;
import com.techouts.eatm.dto.EmployeeDto;
import com.techouts.eatm.dto.EmployeeTechnologyRatingDto;

/**
 * @author Balaji
 *
 */
public interface EmployeeService {
	public EmployeeDto saveEmployee(EmployeeDto dto);

	public String removeEmployee(Long id);

	public EmployeeDto getEmployeeById(Long id);

	public EmployeeDto getEmployeeByName(String name);

	public List<EmployeeDto> getAllEmployees();

	public List<EmployeeDto> getAllEmployeesByTrack(String track);

	public EmployeeDetailsDto empTechRating(Long id);

	public EmployeeTechnologyRatingDto technologyRating(Long empid, Long techid);

	public String updateRating(Long empid, Long techid, int rating);
}
