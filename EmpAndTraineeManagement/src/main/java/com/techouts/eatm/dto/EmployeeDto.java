package com.techouts.eatm.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long empId;
	
	private String empName;
	
	private String dateOfJoining;
	
	private String trainingTrack;

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

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getTrainingTrack() {
		return trainingTrack;
	}

	public void setTrainingTrack(String trainingTrack) {
		this.trainingTrack = trainingTrack;
	}

	public EmployeeDto() {
		super();
	}
	
	
	
}
