package com.techouts.eatm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techouts.eatm.converter.TechnologyConvertor;
import com.techouts.eatm.dao.TechnologyRepository;
import com.techouts.eatm.dto.TechnologyDto;
import com.techouts.eatm.exception.ResourseNotFound;
import com.techouts.eatm.model.Technology;

@Service
public class TechnologyServiceImpl implements TechnologyService {
	@Autowired
	TechnologyRepository technologyDao;

	@Autowired
	TechnologyConvertor technologyConvertor;

	@Autowired
	TrainingTrackServiceImpl trainingTrackService;

	@Override
	public TechnologyDto saveTechnology(TechnologyDto techDto) {

		Technology tech = technologyDao.findByName(techDto.getTechnologyName());

		if (tech == null) {

			Technology technology = technologyDao.save(technologyConvertor.DtoTomodel(techDto));
			return technologyConvertor.modelToDto(technology);
		} else {
			tech.setTechnologyduration(techDto.getTechnologyduration());
			tech.setTechnologyType(techDto.getTechnologyType());
			Technology technology = technologyDao.save(tech);
			return technologyConvertor.modelToDto(technology);
		}

	}

	@Override
	public String removeTechnology(String name) {
		Technology technology = technologyDao.findByName(name);
		if (technology != null) {
			trainingTrackService.removeTechnologyFromTrack(name);
			technologyDao.delete(technology);
			return name + " sucessfullyremoved";
		} else {
			throw new ResourseNotFound("no Technology with name " + name + " found");

		}

	}

	@Override
	public List<TechnologyDto> getTechnologies() {
		return technologyConvertor.modelListToDtoList(technologyDao.findAll());

	}

	@Override
	public List<TechnologyDto> getTechnologiesByType(String type) {

		List<Technology> technologies = technologyDao.findBytype(type);
		if (!technologies.isEmpty()) {
			return technologyConvertor.modelListToDtoList(technologies);
		} else {
			throw new ResourseNotFound("no Technology with type " + type + " found");
		}

	}

	@Override
	public TechnologyDto getBytechnologyName(String name) {
		Technology technology = technologyDao.findByName(name);
		if (technology != null) {
			return technologyConvertor.modelToDto(technology);
		} else {
			throw new ResourseNotFound("no Technology with name " + name + " found");
		}

	}

	public String[] getTechnologysAsArray() {
		List<Technology> technologies = technologyDao.findAll();
		String[] tech = new String[technologies.size()];
		for (int i = 0; i < technologies.size(); i++) {
			tech[i] = technologies.get(i).getTechnologyName();
		}

		return tech;
	}

}
