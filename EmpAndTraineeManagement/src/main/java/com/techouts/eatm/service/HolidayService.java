package com.techouts.eatm.service;

import java.time.LocalDate;
import java.util.List;

import com.techouts.eatm.entity.Holiday;

public interface HolidayService {
	public List<LocalDate> getDatesFromHolidays(List<Holiday> holidays);
}
