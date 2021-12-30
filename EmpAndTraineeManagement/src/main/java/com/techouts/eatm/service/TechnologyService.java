package com.techouts.eatm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techouts.eatm.converter.TechnologyConvertor;
import com.techouts.eatm.dao.TechnologyDao;
import com.techouts.eatm.dto.TechnologyDto;
import com.techouts.eatm.model.Technology;

@Service
public class TechnologyService {
	@Autowired
	TechnologyDao technologyDao;

	@Autowired
	TechnologyConvertor technologyConvertor;

	@Autowired
	TrainingTrackService trainingTrackService;

	public TechnologyDto addTechnologytoDatabase(TechnologyDto techDto) {

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

	public String removeTechnologyfromDatabase(String tech) {
		Technology technology = technologyDao.findByName(tech);
		if (technology != null) {
			trainingTrackService.removeTechnology(tech);
			technologyDao.delete(technology);
			return tech + " sucessfullyremoved";
		} else {
			return tech + " not found";
		}

	}

	public List<TechnologyDto> getTchnologies() {
		return technologyConvertor.modelListToDtoList(technologyDao.findAll());

	}

	public List<TechnologyDto> getTechnologiesByType(String Type) {
		return technologyConvertor.modelListToDtoList(technologyDao.findBytype(Type));

	}

	public TechnologyDto getBytechnologyName(String Name) {
		return technologyConvertor.modelToDto(technologyDao.findByName(Name));
	}

	public String[] gettechnologysasArray() {
		List<Technology> technologies = technologyDao.findAll();
		String[] tech = new String[technologies.size()];
		for (int i = 0; i < technologies.size(); i++) {
			tech[i] = technologies.get(i).getTechnologyName();
		}

		return tech;
	}

}
