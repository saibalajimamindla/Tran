package com.techouts.eatm.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.techouts.eatm.dto.EmployeeDto;
import com.techouts.eatm.entity.Employee;

@Component
public class EmployeeConvertor {

	public EmployeeDto modelToDto(Employee employee) {
		EmployeeDto dto = new EmployeeDto();
		dto.setEmpId(employee.getEmpId());
		dto.setEmpName(employee.getEmpName());
		dto.setDateOfJoining(dateFormatter(employee.getDateOfJoining()));
		dto.setTrainingTrack(employee.getTrainingTrack().getTrackName());
		dto.setTrainingEndDate(dateFormatter(employee.getTrainingEndDate()));
		return dto;

	}

	public List<EmployeeDto> modelListToDtoList(List<Employee> emplList) {
		List<EmployeeDto> dtolist = new ArrayList<>();

		for (Employee employee : emplList) {

			EmployeeDto dto = new EmployeeDto();
			dto.setEmpId(employee.getEmpId());
			dto.setEmpName(employee.getEmpName());
			dto.setDateOfJoining(dateFormatter(employee.getDateOfJoining()));
			dto.setTrainingTrack(employee.getTrainingTrack().getTrackName());
			dto.setTrainingEndDate(dateFormatter(employee.getTrainingEndDate()));
			dtolist.add(dto);

		}
		return dtolist;

	}
	
	public static String dateFormatter(LocalDate date) {
		return DateTimeFormatter.ofPattern("d/MM/yyyy").format(date);

	}
}
