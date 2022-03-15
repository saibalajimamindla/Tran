package com.techouts.eatm.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.techouts.eatm.entity.Holiday;

/**
 * @author Balaji
 *
 */
@Service
public class HolidayServiceImpl implements HolidayService {
	/*
	 * method to get list of holiday dates
	 * 
	 * @param holidays- list of holiday objects
	 * 
	 * @return list of dates
	 */
	
	@Override
	public List<LocalDate> getDatesFromHolidays(List<Holiday> holidays)
	{
		
		if(!holidays.isEmpty())
		{
		return	holidays.stream()
			.map(Holiday::getDate)
			.collect(Collectors.toList());
		}
		return null;
	}

}
