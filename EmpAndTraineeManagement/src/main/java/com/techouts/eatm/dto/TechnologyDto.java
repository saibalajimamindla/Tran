package com.techouts.eatm.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class TechnologyDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String TechnologyName;
	
	private String TechnologyType;
	
	private long Technologyduration;

	public TechnologyDto() {
		super();
	}

	public String getTechnologyName() {
		return TechnologyName;
	}

	public void setTechnologyName(String technologyName) {
		TechnologyName = technologyName;
	}

	public String getTechnologyType() {
		return TechnologyType;
	}

	public void setTechnologyType(String technologyType) {
		TechnologyType = technologyType;
	}

	public long getTechnologyduration() {
		return Technologyduration;
	}

	public void setTechnologyduration(long technologyduration) {
		Technologyduration = technologyduration;
	}

	@Override
	public String toString() {
		return "TechnologyDto [TechnologyName=" + TechnologyName + ", TechnologyType=" + TechnologyType
				+ ", Technologyduration=" + Technologyduration + "]";
	}

	
	
}
