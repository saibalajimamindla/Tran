package com.techouts.eatm.converter;

import org.springframework.stereotype.Component;

import com.techouts.eatm.dto.EmployeeTechnologyRatingDto;
import com.techouts.eatm.entity.EmployeeTechnologyRating;

@Component
public class EmployeeTechnologyRatingConvertor {
	
	public EmployeeTechnologyRatingDto modelToDto(EmployeeTechnologyRating rating ) {
		
		EmployeeTechnologyRatingDto ratingDto = new EmployeeTechnologyRatingDto();
		ratingDto.setEmpid(rating.getEmployee().getEmpId());
		ratingDto.setEmpname(rating.getEmployee().getEmpName());
		ratingDto.setTechid(rating.getTechnology().getId());
		ratingDto.setTechname(rating.getTechnology().getTechnologyName());
		ratingDto.setRating(rating.getRating());
		
		return ratingDto;
		
	}

}
