package com.techouts.eatm.model;

import java.io.Serializable;

public class TechnolgyRating implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long technologyId;

	private String technology;

	private int rating;

	public TechnolgyRating() {
		super();
	}

	public TechnolgyRating(String technology, long technologyId, int rating) {
		super();
		this.technology = technology;
		this.technologyId = technologyId;
		this.rating = rating;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public long getTechnologyId() {
		return technologyId;
	}

	public void setTechnologyId(long technologyId) {
		this.technologyId = technologyId;
	}

}
