package com.techouts.eatm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.techouts.eatm.dto.TechnologyDto;
import com.techouts.eatm.dto.TrainingTrackDto;
import com.techouts.eatm.service.TechnologyService;
import com.techouts.eatm.service.TrainingTrackService;

@Controller
@RequestMapping("/TrainingTrack")
public class TrainingTrackController {

	@Autowired
	TrainingTrackService trainingTrackService;

	@Autowired
	TechnologyService technologyService;
	
	final ModelAndView andView=new ModelAndView();

	@GetMapping("/get")
	public TrainingTrackDto getTraingTrack() {
		TrainingTrackDto trackDto = new TrainingTrackDto();
		trackDto.setTechnologies(technologyService.gettechnologysasArray());
		return trackDto;
	}
	
	@GetMapping("/addTracks")
	//@ResponseBody
	public ModelAndView addTechnology()
	{
	    andView.addObject("addTrack", new TrainingTrackDto());
		andView.setViewName("AddTrainingTrack");
	    return andView;
	}
  
	@PostMapping("/addTrainingTrack")
	public ModelAndView addTraingTracks(@ModelAttribute("addtrack") TrainingTrackDto trainingTrackDto) {
		TrainingTrackDto dto=trainingTrackService.savetracktoDatabase(trainingTrackDto);
		andView.addObject("getTrack", dto);
		andView.setViewName("addedTrack");
		return andView;
	}

	@DeleteMapping("/remove/{name}")
	public String removeTraingTrack(@PathVariable String name) {
		return trainingTrackService.removeTrack(name);

	}

	@GetMapping("/get/{name}")
	public TrainingTrackDto updateTraingTrack(@PathVariable String name) {
		return trainingTrackService.getTrack(name);

	}

	@GetMapping("/getAllTracks")
	public ModelAndView getTraingTracks() {
		List<TrainingTrackDto> dto=trainingTrackService.getTracks();
		andView.addObject("tracks", dto);
		andView.setViewName("allTrainingTracks");
		return andView;
	}

}
