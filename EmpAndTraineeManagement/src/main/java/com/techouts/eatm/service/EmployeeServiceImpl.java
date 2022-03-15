package com.techouts.eatm.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techouts.eatm.converter.EmployeeConvertor;
import com.techouts.eatm.converter.EmployeeTechnologyRatingConvertor;
import com.techouts.eatm.dao.EmployeeRepository;
import com.techouts.eatm.dao.EmployeeTechnologyRatingRepository;
import com.techouts.eatm.dao.HolidayRepository;
import com.techouts.eatm.dao.TrainingTrackRepository;
import com.techouts.eatm.dto.EmployeeDetailsDto;
import com.techouts.eatm.dto.EmployeeDto;
import com.techouts.eatm.dto.EmployeeTechnologyRatingDto;
import com.techouts.eatm.entity.Employee;
import com.techouts.eatm.entity.EmployeeTechnologyRating;
import com.techouts.eatm.entity.Holiday;
import com.techouts.eatm.entity.Technology;
import com.techouts.eatm.entity.TechnologyRatingKey;
import com.techouts.eatm.entity.TrainingTrack;
import com.techouts.eatm.enums.Ratings;
import com.techouts.eatm.exception.ResourseNotFound;
import com.techouts.eatm.model.TechnolgyRating;

/**
 * @author Balaji
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

	Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeRepository employeeDao;

	@Autowired
	TrainingTrackRepository trainingTrackDao;

	@Autowired
	EmployeeConvertor employeeConvertor;

	@Autowired
	EmployeeTechnologyRatingConvertor employeeTechnologyRatingConvertor;

	@Autowired
	HolidayRepository holidayDao;

	@Autowired
	HolidayService holidayService;

	@Autowired
	TrainingTrackService trainingTrackService;

	@Autowired
	EmployeeTechnologyRatingRepository employeeTechnologyRatingDao;

	/*
	 * method to save the employee and update the employee if present
	 * 
	 * @param employeeDto Object
	 * 
	 * @return saved employee
	 */
	@Override
	public EmployeeDto saveEmployee(EmployeeDto dto) {
		Employee checkEmployee = employeeDao.getById(dto.getEmpId());
		if (checkEmployee == null) {
			Employee employee = new Employee();
			employee.setEmpId(dto.getEmpId());
			employee.setEmpName(dto.getEmpName());
			if (dto.getDateOfJoining() != null) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
				LocalDate date = LocalDate.parse(dto.getDateOfJoining(), formatter);
				employee.setDateOfJoining(date);
			} else {
				employee.setDateOfJoining(LocalDate.now());
			}
			if (dto.getTrainingTrack() != null) {
				employee.setTrainingTrack(trainingTrackDao.findByName(dto.getTrainingTrack()));
			} else {
				employee.setTrainingTrack(trainingTrackDao.findByName("default"));
			}
			List<Holiday> holidays = checkForHolidays();
			if (!holidays.isEmpty()) {
				employee.setTrainingEndDate(endDateCalculatorWithHolidays(employee.getDateOfJoining(),
						employee.getTrainingTrack().getTrackDuration(), holidayService.getDatesFromHolidays(holidays)));
			} else {
				employee.setTrainingEndDate(endDateCalculatorWithoutHolidays(employee.getDateOfJoining(),
						employee.getTrainingTrack().getTrackDuration()));

			}
			employee.setAverageRating(0.0);
			Employee savedEmployee = employeeDao.save(employee);
			createDefaultEmployeeRating(savedEmployee);
			return employeeConvertor.modelToDto(savedEmployee);
		} else {
			checkEmployee.setEmpName(dto.getEmpName());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
			LocalDate date = LocalDate.parse(dto.getDateOfJoining(), formatter);
			checkEmployee.setDateOfJoining(date);
			if (!dto.getTrainingTrack().equals(checkEmployee.getTrainingTrack().getTrackName())) {
				checkEmployee.setTrainingTrack(trainingTrackDao.findByName(dto.getTrainingTrack()));
				changeTrainingTrack(checkEmployee);
			}
			checkEmployee.setAverageRating(getAverageRating(checkEmployee.getId()));
			List<Holiday> holidays = checkForHolidays();
			if (!holidays.isEmpty()) {
				checkEmployee.setTrainingEndDate(endDateCalculatorWithHolidays(checkEmployee.getDateOfJoining(),
						checkEmployee.getTrainingTrack().getTrackDuration(),
						holidayService.getDatesFromHolidays(holidays)));
			} else {
				checkEmployee.setTrainingEndDate(endDateCalculatorWithoutHolidays(checkEmployee.getDateOfJoining(),
						checkEmployee.getTrainingTrack().getTrackDuration()));
			}
			return employeeConvertor.modelToDto(employeeDao.save(checkEmployee));
		}

	}

	/*
	 * method to remove the employee by empid
	 * 
	 * @param empid- empid of the employee
	 * 
	 * @return string response
	 */
	@Override
	public String removeEmployee(Long empid) {
		Employee employee = employeeDao.getById(empid);
		if (employee != null) {
			employee.setTrainingTrack(null);
			Employee emp = employeeDao.save(employee);
			removeAllRatings(employee.getId());
			employeeDao.delete(emp);
			return employee.getEmpName() + " with " + employee.getEmpId() + " removed successfully";
		} else {
			throw new ResourseNotFound("employeee with id  " + empid + " not found");
		}

	}

	/*
	 * method to get employee with empid
	 * 
	 * @param empid- empid of the employee
	 * 
	 * @return EmployeeDto
	 */
	@Override
	public EmployeeDto getEmployeeById(Long empid) {
		Employee employee = employeeDao.getById(empid);
		if (employee != null) {

			return employeeConvertor.modelToDto(employee);
		} else {
			throw new ResourseNotFound("employeee with id  " + empid + " not found");
		}

	}

	/*
	 * method to get employee with Employee name
	 * 
	 * @param name - name of the employee
	 * 
	 * @return EmployeeDto
	 */

	@Override
	public EmployeeDto getEmployeeByName(String name) {
		Employee employee = employeeDao.getByName(name);
		if (employee != null) {

			return employeeConvertor.modelToDto(employee);
		} else {
			throw new ResourseNotFound("employeee with name  " + name + " not found");
		}

	}

	/*
	 * method to get all the employees
	 * 
	 * @return list of employees
	 */
	@Override
	public List<EmployeeDto> getAllEmployees() {
		return employeeConvertor.modelListToDtoList(employeeDao.findAll());
	}

	/*
	 * method to get all the employees based on track
	 * 
	 * @return list of employees
	 */
	@Override
	public List<EmployeeDto> getAllEmployeesByTrack(String track) {
		TrainingTrack trainingTrack = trainingTrackDao.findByName(track);
		List<Employee> employeesList = null;
		if (trainingTrack != null) {

			employeesList = employeeDao.getByTrack(trainingTrack.getId());
			return employeeConvertor.modelListToDtoList(employeesList);
		} else
			throw new ResourseNotFound("no track with track name  " + track + " found");

	}

	/*
	 * method to calculate the end date of the training with holidays
	 * 
	 * @param localDate - date of joining of the employee
	 * 
	 * @param days - numbers days that would take to complete the training
	 * 
	 * @param holidays- list of holidays that are to be added during the end date
	 * calculation
	 * 
	 * @return end date of the training
	 */
	public LocalDate endDateCalculatorWithHolidays(LocalDate localDate, long days, List<LocalDate> holidays) {

		if (localDate == null || days <= 0 || holidays == null) {
			System.out.println(localDate);
			System.out.println(days);
			System.out.println(holidays);
			throw new IllegalArgumentException("Invalid method argument(s) " + "to addBusinessDays(" + localDate + ","
					+ days + "," + holidays + ")");
		}

		Predicate<LocalDate> isHoliday = date -> !holidays.isEmpty() ? holidays.contains(date) : false;

		Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
				|| date.getDayOfWeek() == DayOfWeek.SUNDAY;

		LocalDate result = localDate;
		while (days > 0) {
			result = result.plusDays(1);
			if (isHoliday.or(isWeekend).negate().test(result)) {
				days--;
			}
		}
		return result;
	}

	/*
	 * method to calculate the end date of the training without holidays
	 * 
	 * @param localDate - date of joining of the employee
	 * 
	 * @param days - numbers days that would take to complete the training
	 * 
	 * @param holidays- list of holidays that are to be added during the end date
	 * calculation
	 * 
	 * @return end date of the training
	 */
	public LocalDate endDateCalculatorWithoutHolidays(LocalDate localDate, long days) {

		if (localDate == null || days <= 0) {
			throw new IllegalArgumentException(
					"Invalid method argument(s) " + "to addBusinessDays(" + localDate + "," + days + ")");
		}

		Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
				|| date.getDayOfWeek() == DayOfWeek.SUNDAY;

		LocalDate result = localDate;
		while (days > 0) {
			result = result.plusDays(1);
			if (isWeekend.negate().test(result)) {
				days--;
			}
		}
		return result;
	}

	/*
	 * method to check for holidays
	 * 
	 * @return list of holidays
	 */
	public List<Holiday> checkForHolidays() {
		List<Holiday> holidays = holidayDao.findAll();
		if (holidays.isEmpty()) {
			return holidays;
		} else {
			return Collections.emptyList();
		}

	}

	/*
	 * method to create a default ratings for a new employee
	 * 
	 * @param employee- employee object for employee details
	 */
	public void createDefaultEmployeeRating(Employee employee) {
		List<Technology> technologies = trainingTrackService.getTechnologiesFromtrack(employee.getTrainingTrack());

		List<EmployeeTechnologyRating> ratings = new ArrayList<>();

		for (Technology technology : technologies) {

			ratings.add(new EmployeeTechnologyRating(new TechnologyRatingKey(employee.getId(), technology.getId()),
					technology, employee, Ratings.DEFAULTVALUE.getRating()));
		}

		for (EmployeeTechnologyRating employeeTechnologyRating : ratings) {
			EmployeeTechnologyRating rating = employeeTechnologyRatingDao.save(employeeTechnologyRating);
			System.out.println(rating.getTechnology().getTechnologyName());
		}

	}

	/*
	 * method that return all technology ratings of the employee based on empid of
	 * employee
	 * 
	 * @param empid - empid of the employee
	 * 
	 * @return EmployeeDetailsDto
	 */
	@Override
	public EmployeeDetailsDto empTechRating(Long empid) {
		Employee checkEmployee = employeeDao.getById(empid);
		if (checkEmployee != null) {

			EmployeeDetailsDto employeeDetails = new EmployeeDetailsDto();

			employeeDetails.setEmployeeDto(employeeConvertor.modelToDto(checkEmployee));

			List<EmployeeTechnologyRating> employeeTechnologyRatings = employeeTechnologyRatingDao
					.getbyEmpid(checkEmployee.getId());

			List<TechnolgyRating> ratings = new ArrayList<>();

			for (EmployeeTechnologyRating employeeTechnologyRating : employeeTechnologyRatings) {
				ratings.add(new TechnolgyRating(employeeTechnologyRating.getTechnology().getTechnologyName(),
						employeeTechnologyRating.getTechnology().getId(), employeeTechnologyRating.getRating()));
			}

			employeeDetails.setTechnolgyRatings(ratings);
			return employeeDetails;

		} else {
			throw new ResourseNotFound("employeee with id  " + empid + " not found");
		}

	}

	/*
	 * method to remove all the Technology ratings of the employee
	 * 
	 * @param id- id of the employee
	 */
	public void removeAllRatings(Long id) {

		employeeTechnologyRatingDao.removeEmployeeRatings(id);

	}

	/*
	 * method to get specific technology rating of a employee
	 * 
	 * @param empid- empid of the employee
	 * 
	 * @param techid- id of the technology
	 * 
	 * @return EmployeeTechnologyRatingDto
	 */
	@Override
	public EmployeeTechnologyRatingDto technologyRating(Long empid, Long techid) {
		Employee checkEmployee = employeeDao.getById(empid);
		if (checkEmployee != null) {
			EmployeeTechnologyRating rating = employeeTechnologyRatingDao.getTechRating(checkEmployee.getId(), techid);

			return employeeTechnologyRatingConvertor.modelToDto(rating);

		} else {
			throw new ResourseNotFound("employeee with id  " + empid + " not found");
		}

	}

	/*
	 * method to update the rating of the technology
	 * 
	 * @param empid- empid of the employee
	 * 
	 * @param techid- id of the technology
	 * 
	 * @param rating- updated rating of the employee
	 */
	@Override
	public String updateRating(Long empid, Long techid, int rating) {
		Employee checkEmployee = employeeDao.getById(empid);
		if (checkEmployee != null) {
			employeeTechnologyRatingDao.updateRating(checkEmployee.getId(), techid, rating);
			return "Rating of employee with id " + empid + " is sucessfully updated ";
		} else {
			throw new ResourseNotFound("employeee with id  " + empid + " not found");
		}
	}

	/*
	 * method to calculate the average rating f the employee
	 * 
	 * @ return double
	 */
	public double getAverageRating(Long id) {
		return employeeTechnologyRatingDao.getAverageRating(id);
	}

	/*
	 * method to change the training track of the employee
	 * 
	 * @param employee - employee object
	 */
	public void changeTrainingTrack(Employee employee) {
		List<EmployeeTechnologyRating> previousratings = employeeTechnologyRatingDao.getbyEmpid(employee.getId());

		List<Technology> oldTrackTechnologies = previousratings.stream().map(rating -> rating.getTechnology())
				.collect(Collectors.toList());
		List<Technology> newTrackTechnologies = trainingTrackService
				.getTechnologiesFromtrack(employee.getTrainingTrack());

		for (Technology technology : newTrackTechnologies) {

			if (!oldTrackTechnologies.contains(technology)) {
				addRating(new EmployeeTechnologyRating(new TechnologyRatingKey(employee.getId(), technology.getId()),
						technology, employee, Ratings.DEFAULTVALUE.getRating()));
			}

		}

		for (Technology oldtechnology : oldTrackTechnologies) {
			if (!newTrackTechnologies.contains(oldtechnology)) {
				removeRating(employee.getId(), oldtechnology.getId());
			}
		}

	}

	public void removeRating(Long empid, Long techid) {
		employeeTechnologyRatingDao.removeEmployeeRating(empid, techid);
	}

	public void addRating(EmployeeTechnologyRating rating) {
		employeeTechnologyRatingDao.save(rating);
	}

}
