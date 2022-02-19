package com.techouts.eatm.enums;

public enum Ratings {
	
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	DEFAULTVALUE(0);
	
	private int rating;

	private Ratings(int rating) {
		this.setRating(rating);
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	
	

}
