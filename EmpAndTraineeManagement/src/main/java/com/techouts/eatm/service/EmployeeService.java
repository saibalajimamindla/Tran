package com.techouts.eatm.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techouts.eatm.converter.EmployeeConvertor;
import com.techouts.eatm.dao.EmployeeDao;
import com.techouts.eatm.dao.TrainingTrackDao;
import com.techouts.eatm.dto.EmployeeDto;
import com.techouts.eatm.model.Employee;
import com.techouts.eatm.model.TrainingTrack;

@Service
public class EmployeeService {

	Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	EmployeeDao employeeDao;

	@Autowired
	TrainingTrackDao trainingTrackDao;

	@Autowired
	EmployeeConvertor employeeConvertor;

	public EmployeeDto saveEmployee(EmployeeDto dto) {
		Employee checkEmployee = employeeDao.getById(dto.getEmpId());
		if (checkEmployee == null) {
			Employee employee = new Employee();
			employee.setEmpId(dto.getEmpId());
			employee.setEmpName(dto.getEmpName());
			String pattern = "dd/mm/yyyy";
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			Date date = null;
			try {
				date = format.parse(dto.getDateOfJoining());
			} catch (ParseException e) {

				e.printStackTrace();
				logger.info("parse exception in employee date of joining");
			}
			employee.setDateOfJoining(date);
			if (dto.getTrainingTrack() != null) {
				employee.setTrainingTrack(trainingTrackDao.findByName(dto.getTrainingTrack()));
			} else {
				employee.setTrainingTrack(trainingTrackDao.findByName("default"));
			}

			return employeeConvertor.modelToDto(employeeDao.save(employee));
		} else {
			checkEmployee.setEmpName(dto.getEmpName());
			String pattern = "dd/mm/yyyy";
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			Date date = null;
			try {
				date = format.parse(dto.getDateOfJoining());
			} catch (ParseException e) {

				e.printStackTrace();
				logger.info("parse exception in employee date of joining");
			}
			checkEmployee.setDateOfJoining(date);
			checkEmployee.setTrainingTrack(trainingTrackDao.findByName(dto.getTrainingTrack()));

			return employeeConvertor.modelToDto(employeeDao.save(checkEmployee));
		}

	}

	public String removeEmployee(Long id) {
		Employee employee = employeeDao.getById(id);
		if (employee != null) {
			employee.setTrainingTrack(null);
			Employee emp =	employeeDao.save(employee);
			employeeDao.delete(emp);
			
			return employee.getEmpName()+" with "+employee.getEmpId()+" removed successfully";
		}
		else {
			return "Employee not found";
		}

	}

	public EmployeeDto getEmployeeById(Long id) {
		Employee employee = employeeDao.getById(id);
		if (employee != null) {

			return employeeConvertor.modelToDto(employee);
		} else {
			return null;
		}

	}

	public EmployeeDto getEmployeeByName(String name) {
		Employee employee = employeeDao.getByName(name);
		if (employee != null) {

			return employeeConvertor.modelToDto(employee);
		} else {
			return null;
		}

	}

	public List<EmployeeDto> getAllEmployees() {
		return employeeConvertor.modelListToDtoList(employeeDao.findAll());
	}

	public List<EmployeeDto> getAllEmployeesByTrack(String track) {
		TrainingTrack trainingTrack = trainingTrackDao.findByName(track);
		List<Employee> employeesList = null;
		if (trainingTrack != null) {

			employeesList = employeeDao.getByTrack(trainingTrack.getId());
		}
		return employeeConvertor.modelListToDtoList(employeesList);
	}

}
