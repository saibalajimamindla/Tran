package com.techouts.eatm.dto;

import java.io.Serializable;
import java.util.List;

import com.techouts.eatm.model.TechnolgyRating;

public class EmployeeDetailsDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EmployeeDto employeeDto;
	
	private List<TechnolgyRating> technolgyRatings;

	public EmployeeDetailsDto() {
		super();
	}

	public EmployeeDto getEmployeeDto() {
		return employeeDto;
	}

	public void setEmployeeDto(EmployeeDto employeeDto) {
		this.employeeDto = employeeDto;
	}

	public List<TechnolgyRating> getTechnolgyRatings() {
		return technolgyRatings;
	}

	public void setTechnolgyRatings(List<TechnolgyRating> technolgyRatings) {
		this.technolgyRatings = technolgyRatings;
	}

		
	

}
