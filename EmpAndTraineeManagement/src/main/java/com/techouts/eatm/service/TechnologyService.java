package com.techouts.eatm.service;

import java.util.List;

import com.techouts.eatm.dto.TechnologyDto;

public interface TechnologyService {

	public TechnologyDto saveTechnology(TechnologyDto techDto);

	public String removeTechnology(String tech);

	public List<TechnologyDto> getTechnologies();

	public List<TechnologyDto> getTechnologiesByType(String Type);

	public TechnologyDto getBytechnologyName(String Name);

	public String[] getTechnologysAsArray();


}
