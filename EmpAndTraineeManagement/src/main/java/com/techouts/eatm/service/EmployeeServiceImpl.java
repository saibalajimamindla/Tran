package com.techouts.eatm.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techouts.eatm.converter.EmployeeConvertor;
import com.techouts.eatm.dao.EmployeeRepository;
import com.techouts.eatm.dao.EmployeeTechnologyRatingRepository;
import com.techouts.eatm.dao.HolidayRepository;
import com.techouts.eatm.dao.TrainingTrackRepository;
import com.techouts.eatm.dto.EmployeeDetailsDto;
import com.techouts.eatm.dto.EmployeeDto;
import com.techouts.eatm.entity.Employee;
import com.techouts.eatm.entity.EmployeeTechnologyRating;
import com.techouts.eatm.entity.Holiday;
import com.techouts.eatm.entity.Technology;
import com.techouts.eatm.entity.TrainingTrack;
import com.techouts.eatm.exception.ResourseNotFound;
import com.techouts.eatm.model.TechnolgyRating;

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
	HolidayRepository holidayDao;

	@Autowired
	HolidayService holidayService;

	@Autowired
	TrainingTrackService trainingTrackService;

	@Autowired
	EmployeeTechnologyRatingRepository employeeTechnologyRatingDao;

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

			return employeeConvertor.modelToDto(employeeDao.save(employee));
		} else {
			checkEmployee.setEmpName(dto.getEmpName());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
			LocalDate date = LocalDate.parse(dto.getDateOfJoining(), formatter);
			checkEmployee.setDateOfJoining(date);
			checkEmployee.setTrainingTrack(trainingTrackDao.findByName(dto.getTrainingTrack()));
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

	@Override
	public String removeEmployee(Long id) {
		Employee employee = employeeDao.getById(id);
		if (employee != null) {
			employee.setTrainingTrack(null);
			Employee emp = employeeDao.save(employee);
			employeeDao.delete(emp);
			return employee.getEmpName() + " with " + employee.getEmpId() + " removed successfully";
		} else {
			throw new ResourseNotFound("employeee with id  " + id + " not found");
		}

	}

	@Override
	public EmployeeDto getEmployeeById(Long id) {
		Employee employee = employeeDao.getById(id);
		if (employee != null) {

			return employeeConvertor.modelToDto(employee);
		} else {
			throw new ResourseNotFound("employeee with id  " + id + " not found");
		}

	}

	@Override
	public EmployeeDto getEmployeeByName(String name) {
		Employee employee = employeeDao.getByName(name);
		if (employee != null) {

			return employeeConvertor.modelToDto(employee);
		} else {
			throw new ResourseNotFound("employeee with name  " + name + " not found");
		}

	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		return employeeConvertor.modelListToDtoList(employeeDao.findAll());
	}

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

	public List<Holiday> checkForHolidays() {
		List<Holiday> holidays = holidayDao.findAll();
		if (holidays.isEmpty()) {
			return holidays;
		} else {
			return null;
		}

	}

	@Override
	public EmployeeDetailsDto rateEmployee(Long id) {
		Employee checkEmployee = employeeDao.getById(id);
		if (checkEmployee != null) {

			EmployeeDetailsDto employeeDetails = new EmployeeDetailsDto();
			employeeDetails.setEmployeeDto(employeeConvertor.modelToDto(checkEmployee));
			;

			List<EmployeeTechnologyRating> technologyRatings = employeeTechnologyRatingDao
					.getbyEmpid(checkEmployee.getId());

			List<Technology> technologies = trainingTrackService
					.getTechnologiesFromtrack(checkEmployee.getTrainingTrack());

			System.out.println(technologies.size());

			System.out.println(technologyRatings.size());

			List<TechnolgyRating> ratings = new ArrayList<>();

			for (Technology technology : technologies) {

				ratings.add(new TechnolgyRating(technology.getTechnologyName(),
						getTechnologyrating(technologyRatings, technology)));
			}

			employeeDetails.setTechnolgyRatings(ratings);

			return employeeDetails;
		} else {
			throw new ResourseNotFound("employeee with id  " + id + " not found");
		}

	}

	public int getTechnologyrating(List<EmployeeTechnologyRating> ratings, Technology technology) {

		int rating = 0;

		List<EmployeeTechnologyRating> value = ratings.stream()
				.filter(techRating -> techRating.getTechnology().equals(technology)).collect(Collectors.toList());
		if (!value.isEmpty()) {
			rating = value.get(0).getRating();
		}

		return rating;
	}

	public void removeRatings(Long id) {
		employeeTechnologyRatingDao.removeEmployeeRatings(id);
	
	}
}
