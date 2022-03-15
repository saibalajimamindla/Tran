package com.techouts.eatm.dto;


/**
 * @author Balaji
 *
 */

public class EmployeeTechnologyRatingDto {

	Long empid;

	String empname;

	Long techid;

	String techname;

	int rating;

	public EmployeeTechnologyRatingDto() {
		super();
	}

	public Long getEmpid() {
		return empid;
	}

	public void setEmpid(Long empid) {
		this.empid = empid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public Long getTechid() {
		return techid;
	}

	public void setTechid(Long techid) {
		this.techid = techid;
	}

	public String getTechname() {
		return techname;
	}

	public void setTechname(String techname) {
		this.techname = techname;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	

}
