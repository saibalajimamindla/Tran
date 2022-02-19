package com.techouts.eatm.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TechnologyRatingKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "student_id")
	Long empid;

	@Column(name = "course_id")
	Long techid;

	public TechnologyRatingKey() {
		super();
	}

	public TechnologyRatingKey(Long empid, Long techid) {
		super();
		this.empid = empid;
		this.techid = techid;
	}

	public Long getEmpid() {
		return empid;
	}

	public void setEmpid(Long empid) {
		this.empid = empid;
	}

	public Long getTechid() {
		return techid;
	}

	public void setTechid(Long techid) {
		this.techid = techid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(empid, techid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TechnologyRatingKey other = (TechnologyRatingKey) obj;
		return Objects.equals(empid, other.empid) && Objects.equals(techid, other.techid);
	}
	

}
