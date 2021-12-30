package com.techouts.eatm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techouts.eatm.dto.TechnologyDto;
import com.techouts.eatm.service.TechnologyService;

@RestController
@RequestMapping("/technology")
public class TechnologyController {
	
	@Autowired
	TechnologyService technologyService;
	
	
	
	@GetMapping("/add")
	@ResponseBody
	public TechnologyDto addTechnology()
	{
		return new TechnologyDto();
	}
	
	@PostMapping("/add")
	@ResponseBody
	public TechnologyDto addTechnology(@ModelAttribute TechnologyDto technologyDto)
	{
		return technologyService.addTechnologytoDatabase(technologyDto);
	}
	
	@PutMapping("/{name}")
	public TechnologyDto updateTechnology(@PathVariable String name)
	{
		return technologyService.getBytechnologyName(name);
	}
	
	
	@DeleteMapping("/{name}")
	public String removeTechnology(@PathVariable String name)
	{
		return technologyService.removeTechnologyfromDatabase(name);
	}
	
	@GetMapping("/all")
	public List<TechnologyDto> getTechnologies()
	{
		return technologyService.getTchnologies();
	}
	
	@GetMapping("/all/{type}")
	public List<TechnologyDto> getTechnologiesByType(@PathVariable String type)
	{
		return technologyService.getTechnologiesByType(type);
	}
	

	

}
