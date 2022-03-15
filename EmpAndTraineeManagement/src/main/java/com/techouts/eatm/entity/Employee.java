package com.techouts.eatm.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "emp_id", unique = true)
	private long empId;

	@Column(unique = true, name = "emp_name")
	private String empName;

	@Column(name = "date_of_joining")
	private LocalDate dateOfJoining;

	@Column(name = "training_end_date")
	private LocalDate trainingEndDate;
	
	@Column(name = "average_rating")
	private Double averageRating;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "track_id", referencedColumnName = "track_id")
	private TrainingTrack trainingTrack;
	
	@OneToMany(mappedBy = "employee")
    Set<EmployeeTechnologyRating> ratings;

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TrainingTrack getTrainingTrack() {
		return trainingTrack;
	}

	public void setTrainingTrack(TrainingTrack trainingTrack) {
		this.trainingTrack = trainingTrack;
	}

	public LocalDate getTrainingEndDate() {
		return trainingEndDate;
	}

	public void setTrainingEndDate(LocalDate trainingEndDate) {
		this.trainingEndDate = trainingEndDate;
	}
	
	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}

	public Set<EmployeeTechnologyRating> getRatings() {
		return ratings;
	}

	public void setRatings(Set<EmployeeTechnologyRating> ratings) {
		this.ratings = ratings;
	}

	public Employee() {
		super();
	}

}
