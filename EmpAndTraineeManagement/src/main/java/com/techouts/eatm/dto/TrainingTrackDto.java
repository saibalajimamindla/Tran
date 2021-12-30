package com.techouts.eatm.dto;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class TrainingTrackDto implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private String trackname;

private String [] technologies;

public TrainingTrackDto() {
	super();
}

public String getTrackname() {
	return trackname;
}

public void setTrackname(String trackname) {
	this.trackname = trackname;
}

public String[] getTechnologies() {
	return technologies;
}

public void setTechnologies(String[] technologies) {
	this.technologies = technologies;
}



}
