package com.techouts.eatm.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techouts.eatm.converter.TrainingTrackConvertor;
import com.techouts.eatm.dao.TechnologyDao;
import com.techouts.eatm.dao.TrainingTrackDao;
import com.techouts.eatm.dto.TrainingTrackDto;
import com.techouts.eatm.model.Technology;
import com.techouts.eatm.model.TrainingTrack;

@Service
public class TrainingTrackService {
	@Autowired
	TrainingTrackDao trainingTrackDao;

	@Autowired
	TrainingTrackConvertor trainingTrackConvertor;

	@Autowired
	TechnologyDao technologyDao;

	public TrainingTrackDto savetracktoDatabase(TrainingTrackDto trainingTrackDto) {
		TrainingTrack trainingTrack = trainingTrackDao.findByName(trainingTrackDto.getTrackname());
		if (trainingTrack == null) {
			TrainingTrack track = new TrainingTrack();
			track.setTrackName(trainingTrackDto.getTrackname());
			track.setTechnologies(getTechnologysFromArray(trainingTrackDto));
			track.setTrackDuration(technologyDayCount(getTechnologysFromArray(trainingTrackDto)));
			System.out.println(technologyDayCount(getTechnologysFromArray(trainingTrackDto)));

			return trainingTrackConvertor.modelToDto(trainingTrackDao.save(track));
		} else {
			trainingTrack.getTechnologies().clear();
			trainingTrack.setTechnologies(getTechnologysFromArray(trainingTrackDto));
			trainingTrack.setTrackDuration(technologyDayCount(getTechnologysFromArray(trainingTrackDto)));
			System.out.println(technologyDayCount(getTechnologysFromArray(trainingTrackDto)));

			return trainingTrackConvertor.modelToDto(trainingTrackDao.save(trainingTrack));
		}

	}

	public long technologyDayCount(Set<Technology> technologies) {
		long count = 0;
		for (Technology technology : technologies) {
			count = count + technology.getTechnologyduration();
		}
		return count;

	}

	public Set<Technology> getTechnologysFromArray(TrainingTrackDto trainingTrackDto) {
		Set<Technology> technologies = new LinkedHashSet<Technology>();
		String[] tech = trainingTrackDto.getTechnologies();
		for (int i = 0; i < trainingTrackDto.getTechnologies().length; i++) {

			technologies.add(technologyDao.findByName(tech[i]));
		}
		return technologies;

	}

	public String removeTrack(String name) {
		TrainingTrack trainingTrack = trainingTrackDao.findByName(name);
		if (trainingTrack != null) {
			trainingTrack.getTechnologies().clear();
			trainingTrack.setTechnologies(null);
			trainingTrackDao.save(trainingTrack);
			trainingTrackDao.delete(trainingTrack);
			return name + " track removed";
		} else {
			return name + " track notfound";
		}

	}

	public TrainingTrackDto getTrack(String name) {
		TrainingTrack trainingTrack = trainingTrackDao.findByName(name);

		System.out.println(trainingTrack.getTrackName());

		return trainingTrackConvertor.modelToDto(trainingTrack);
	}

	public List<TrainingTrackDto> getTracks() {
		return trainingTrackConvertor.modelListToDtoList(trainingTrackDao.findAll());
	}

	public void removeTechnology(String name) {

		List<TrainingTrack> tracks = trainingTrackDao.findAll();
		for (TrainingTrack track : tracks) {
			Set<Technology> technologies = track.getTechnologies();
			technologies.remove(technologyDao.findByName(name));
			trainingTrackDao.save(track);

		}
	}

}
