package com.techouts.eatm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.techouts.eatm.dto.TechnologyDto;
import com.techouts.eatm.service.TechnologyService;

@Controller
@RequestMapping("/technology")
public class TechnologyController
{
	@Autowired
	TechnologyService technologyService;
	
	final  ModelAndView andView=new ModelAndView();
	
	@GetMapping("/AddTechnologies")
	//@ResponseBody
	public ModelAndView addTechnology()
	{
	    andView.addObject("addTechnology", new TechnologyDto());
		andView.setViewName("AddTechnology");
	    return andView;
	}
	
	@PostMapping("/addTech")
	//@ResponseBody
	public ModelAndView addTechnology(@ModelAttribute("addTechnology") TechnologyDto technologyDto)
	{
		TechnologyDto dto=technologyService.addTechnologytoDatabase(technologyDto);
		andView.addObject("add", dto);
		andView.setViewName("addedTechnology");
		return andView;
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
	
	@GetMapping("/getAllTechnologies")
	public ModelAndView getTechnologies()
	{
	    List<TechnologyDto> dtos=technologyService.getTchnologies();	
		andView.addObject("technologies", dtos);
		andView.setViewName("allTechnologies");
		//andView.setViewName("AddTraining");
		return andView;
	}
	
	public ModelAndView getTechnologiesForTrack()
	{
	    List<TechnologyDto> dtos=technologyService.getTchnologies();
	    System.out.println(dtos);
		andView.addObject("technologies", dtos);
		andView.setViewName("AddTrainingTrack");
		//andView.setViewName("AddTraining");
		return andView;
	}
	@GetMapping("/all/{type}")
	public List<TechnologyDto> getTechnologiesByType(@PathVariable String type)
	{
		return technologyService.getTechnologiesByType(type);
	}
	

	

}
