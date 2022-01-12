package com.techouts.eatm;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.techouts.eatm.dao.HolidayRepository;
import com.techouts.eatm.model.Holiday;

@SpringBootTest
class EmpAndTraineeManagementApplicationTests {

	@Autowired
	HolidayRepository holidayRepository;

	@Test
	void contextLoads() {

		
	}

}
