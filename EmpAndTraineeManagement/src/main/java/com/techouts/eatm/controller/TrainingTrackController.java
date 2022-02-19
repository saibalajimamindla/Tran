package com.techouts.eatm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techouts.eatm.dto.TrainingTrackDto;
import com.techouts.eatm.service.TechnologyService;
import com.techouts.eatm.service.TrainingTrackService;

@RestController
@RequestMapping("/trainingtrack")
public class TrainingTrackController {

	@Autowired
	TrainingTrackService trainingTrackService;

	@Autowired
	TechnologyService technologyService;

	@GetMapping("/")
	public TrainingTrackDto getTraingTrack() {
		TrainingTrackDto trackDto = new TrainingTrackDto();
		trackDto.setTechnologies(technologyService.getTechnologysAsArray());
		return trackDto;
	}

	@PostMapping("/add")
	public TrainingTrackDto addTraingTrack(@RequestBody TrainingTrackDto trainingTrackDto) {
		return trainingTrackService.saveTrainingTrack(trainingTrackDto);
	}

	@DeleteMapping("/{name}")
	public String removeTraingTrack(@PathVariable String name) {
		return trainingTrackService.removeTrainingTrack(name);

	}

	@GetMapping("/name/{name}")
	public TrainingTrackDto getTrainingTrackByName(@PathVariable String name) {
		return trainingTrackService.getTrainingTrackByName(name);

	}

	@PostMapping("/all")
	public List<TrainingTrackDto> getAllTraingTracks() {
		return trainingTrackService.getAllTrainingTrack();
	}

}
