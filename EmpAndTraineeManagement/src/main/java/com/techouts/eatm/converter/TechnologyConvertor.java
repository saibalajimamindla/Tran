package com.techouts.eatm.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.techouts.eatm.dto.TechnologyDto;
import com.techouts.eatm.entity.Technology;

@Component
public class TechnologyConvertor {

	public TechnologyDto modelToDto(Technology technology) {
		
		TechnologyDto Dto = new TechnologyDto();
		Dto.setTechnologyName(technology.getTechnologyName());
		Dto.setTechnologyType(technology.getTechnologyType());
		Dto.setTechnologyduration(technology.getTechnologyduration());
		
		return Dto;

	}

	public List<TechnologyDto> modelListToDtoList(List<Technology> technologyList) {
		List<TechnologyDto> dtolist = new ArrayList<TechnologyDto>();
		for(Technology technology:technologyList)
		{
			TechnologyDto Dto = new TechnologyDto();
			Dto.setTechnologyName(technology.getTechnologyName());
			Dto.setTechnologyType(technology.getTechnologyType());
			Dto.setTechnologyduration(technology.getTechnologyduration());
			dtolist.add(Dto);
		}
		return dtolist;

	}
	
public Technology DtoTomodel(TechnologyDto technologyDto) {
		
		Technology technology = new Technology();
		technology.setTechnologyName(technologyDto.getTechnologyName());
		technology.setTechnologyType(technologyDto.getTechnologyType());
		technology.setTechnologyduration(technologyDto.getTechnologyduration());
		
		return technology;

	}
	
}
