package com.techouts.eatm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("/")
	@ResponseBody
	public TechnologyDto getTechnology() {
		return new TechnologyDto();
	}

	@PostMapping("/add")
	@ResponseBody
	public TechnologyDto addTechnology(@RequestBody TechnologyDto technologyDto) {
		return technologyService.saveTechnology(technologyDto);
	}

	@DeleteMapping("/{name}")
	public String removeTechnology(@PathVariable String name) {
		return technologyService.removeTechnology(name);
	}

	@GetMapping("/name/{name}")
	public TechnologyDto getTechnologyByName(@PathVariable String name) {
		return technologyService.getBytechnologyName(name);
	}

	@GetMapping("/all")
	public List<TechnologyDto> getAllTechnologies() {
		return technologyService.getTechnologies();
	}

	@GetMapping("/all/{type}")
	public List<TechnologyDto> getTechnologiesByType(@PathVariable String type) {
		return technologyService.getTechnologiesByType(type);
	}

}
