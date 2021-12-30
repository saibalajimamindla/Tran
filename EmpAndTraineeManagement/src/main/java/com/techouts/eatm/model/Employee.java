package com.techouts.eatm.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee  {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name ="emp_id",unique = true)
	private long empId;
	
	@Column(unique = true,name = "emp_name")
	private String empName;
	
	@Column(name = "date_of_joining")
	private Date dateOfJoining;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name ="track_id",referencedColumnName = "id")
	private TrainingTrack trainingTrack;

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

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
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

	public Employee(int empId, String empName, Date dateOfJoining) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.dateOfJoining = dateOfJoining;
	}

	public Employee() {
		super();
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", empId=" + empId + ", empName=" + empName + ", dateOfJoining=" + dateOfJoining
				+ ", trainingTrack=" + trainingTrack + "]";
	}
	
	
	
	
	
	
	
	
	

}
