package com.techouts.eatm.service;

import java.util.List;

import com.techouts.eatm.dto.TrainingTrackDto;
import com.techouts.eatm.entity.Technology;
import com.techouts.eatm.entity.TrainingTrack;

public interface TrainingTrackService {

	public TrainingTrackDto saveTrainingTrack(TrainingTrackDto trainingTrackDto) ;
	
	public String removeTrainingTrack(String name);

	public TrainingTrackDto getTrainingTrackByName(String name);
	
	public List<TrainingTrackDto> getAllTrainingTrack();
	
	public List<Technology> getTechnologiesFromtrack(TrainingTrack track);
	
	
}
