package com.techouts.eatm.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.techouts.eatm.dto.EmployeeDto;
import com.techouts.eatm.model.Employee;

@Component
public class EmployeeConvertor {

	public EmployeeDto modelToDto(Employee employee) {
		EmployeeDto dto = new EmployeeDto();
		dto.setEmpId(employee.getEmpId());
		dto.setEmpName(employee.getEmpName());
		dto.setDateOfJoining(dateFormatter(employee.getDateOfJoining()));
		dto.setTrainingTrack(employee.getTrainingTrack().getTrackName());
		return dto;

	}

	public List<EmployeeDto> modelListToDtoList(List<Employee> emplList) {
		List<EmployeeDto> dtolist = new ArrayList<>();

		for (Employee employee : emplList) {

			EmployeeDto dto = new EmployeeDto();
			dto.setEmpId(employee.getEmpId());
			dto.setEmpName(employee.getEmpName());
			dto.setDateOfJoining(employee.getDateOfJoining().toString());
			dto.setTrainingTrack(employee.getTrainingTrack().getTrackName());
			dtolist.add(dto);

		}
		return dtolist;

	}

	public static String dateFormatter(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyy");
		String strDate = dateFormat.format(date);
		return strDate;
	}
}
