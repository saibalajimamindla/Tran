package com.techouts.eatm.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.techouts.eatm.dto.TrainingTrackDto;
import com.techouts.eatm.model.Technology;
import com.techouts.eatm.model.TrainingTrack;

@Component
public class TrainingTrackConvertor {

	public TrainingTrackDto modelToDto(TrainingTrack trainingTrack) {
		TrainingTrackDto trainingTrackDto = new TrainingTrackDto();
		trainingTrackDto.setTrackname(trainingTrack.getTrackName());
		trainingTrackDto.setTechnologies(trackTechToArray(trainingTrack));

		return trainingTrackDto;
	}

	public String[] trackTechToArray(TrainingTrack trainingTrack) {
		String[] tech = new String[trainingTrack.getTechnologies().size()];

		Set<Technology> technologies = trainingTrack.getTechnologies();
		int i = 0;
		for (Technology technology : technologies) {
			tech[i++] = technology.getTechnologyName();
		}

		return tech;
	}

	public List<TrainingTrackDto> modelListToDtoList(List<TrainingTrack> trainingTracks) {
		List<TrainingTrackDto> trainingTrackDtos = new ArrayList<TrainingTrackDto>();

		for (TrainingTrack trainingTrack : trainingTracks) {
			TrainingTrackDto trackDto = new TrainingTrackDto();
			trackDto.setTrackname(trainingTrack.getTrackName());
			trackDto.setTechnologies(trackTechToArray(trainingTrack));
			trainingTrackDtos.add(trackDto);

		}

		return trainingTrackDtos;
	}

}
