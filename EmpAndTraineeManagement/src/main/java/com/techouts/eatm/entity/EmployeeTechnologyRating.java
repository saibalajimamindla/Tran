package com.techouts.eatm.entity;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class EmployeeTechnologyRating {
	
	@EmbeddedId
	TechnologyRatingKey id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @MapsId("techid")
    @JoinColumn(name = "tech_id")
	Technology technology;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @MapsId("empid")
    @JoinColumn(name = "emp_id")
	Employee employee;
	
	int rating;

	public EmployeeTechnologyRating() {
		super();
	}
	

	public EmployeeTechnologyRating( Technology technology, Employee employee, int rating) {
		super();
		this.id = new TechnologyRatingKey(employee.getEmpId(), technology.getId());
		this.technology = technology;
		this.employee = employee;
		this.rating = rating;
	}


	public TechnologyRatingKey getId() {
		return id;
	}

	public void setId(TechnologyRatingKey id) {
		this.id = id;
	}

	public Technology getTechnology() {
		return technology;
	}

	public void setTechnology(Technology technology) {
		this.technology = technology;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	
}
