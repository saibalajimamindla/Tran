package com.techouts.eatm.service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techouts.eatm.converter.TrainingTrackConvertor;
import com.techouts.eatm.dao.TechnologyRepository;
import com.techouts.eatm.dao.TrainingTrackRepository;
import com.techouts.eatm.dto.TrainingTrackDto;
import com.techouts.eatm.entity.Technology;
import com.techouts.eatm.entity.TrainingTrack;
import com.techouts.eatm.exception.ResourseNotFound;

@Service
public class TrainingTrackServiceImpl implements TrainingTrackService {
	@Autowired
	TrainingTrackRepository trainingTrackDao;

	@Autowired
	TrainingTrackConvertor trainingTrackConvertor;

	@Autowired
	TechnologyRepository technologyDao;

	@Override
	public TrainingTrackDto saveTrainingTrack(TrainingTrackDto trainingTrackDto) {
		TrainingTrack trainingTrack = trainingTrackDao.findByName(trainingTrackDto.getTrackname());
		if (trainingTrack == null) {
			TrainingTrack track = new TrainingTrack();
			track.setTrackName(trainingTrackDto.getTrackname());
			track.setTechnologies(getTechnologysFromArray(trainingTrackDto));
			track.setTrackDuration(technologyDayCount(getTechnologysFromArray(trainingTrackDto)));
			return trainingTrackConvertor.modelToDto(trainingTrackDao.save(track));
		} else {
			trainingTrack.getTechnologies().clear();
			trainingTrack.setTechnologies(getTechnologysFromArray(trainingTrackDto));
			trainingTrack.setTrackDuration(technologyDayCount(getTechnologysFromArray(trainingTrackDto)));
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
		Set<Technology> technologies = new LinkedHashSet<>();
		String[] tech = trainingTrackDto.getTechnologies();
		for (int i = 0; i < trainingTrackDto.getTechnologies().length; i++) {

			technologies.add(technologyDao.findByName(tech[i]));
		}
		return technologies;

	}

	@Override
	public String removeTrainingTrack(String name) {
		TrainingTrack trainingTrack = trainingTrackDao.findByName(name);
		if (trainingTrack != null) {
			trainingTrack.getTechnologies().clear();
			trainingTrack.setTechnologies(null);
			trainingTrackDao.save(trainingTrack);
			trainingTrackDao.delete(trainingTrack);
			return name + " track removed";
		} else {
			throw new ResourseNotFound("no track with track name  "+name+" found"); 
		}

	}

	@Override
	public TrainingTrackDto getTrainingTrackByName(String name) {
		TrainingTrack trainingTrack = trainingTrackDao.findByName(name);
		if(trainingTrack!=null)
		{
			return trainingTrackConvertor.modelToDto(trainingTrack);
		}else
		{
			throw new ResourseNotFound("no track with track name  "+name+" found"); 
			
		}
		
	}

	@Override
	public List<TrainingTrackDto> getAllTrainingTrack() {
		return trainingTrackConvertor.modelListToDtoList(trainingTrackDao.findAll());
	}

	public void removeTechnologyFromTrack(String name) {

		List<TrainingTrack> tracks = trainingTrackDao.findAll();
		for (TrainingTrack track : tracks) {
			Set<Technology> technologies = track.getTechnologies();
			technologies.remove(technologyDao.findByName(name));
			trainingTrackDao.save(track);

		}
	}

	@Override
	public List<Technology> getTechnologiesFromtrack(TrainingTrack track) {
		
		 return track.getTechnologies().stream().collect(Collectors.toList());
	}

}
