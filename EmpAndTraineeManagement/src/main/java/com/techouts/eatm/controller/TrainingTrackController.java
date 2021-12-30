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
@RequestMapping("/TraingTrack")
public class TrainingTrackController {

	@Autowired
	TrainingTrackService trainingTrackService;

	@Autowired
	TechnologyService technologyService;

	@GetMapping("/")
	public TrainingTrackDto getTraingTrack() {
		TrainingTrackDto trackDto = new TrainingTrackDto();
		trackDto.setTechnologies(technologyService.gettechnologysasArray());
		return trackDto;
	}

	@PostMapping("/add")
	public TrainingTrackDto addTraingTracks(@RequestBody TrainingTrackDto trainingTrackDto) {
		return trainingTrackService.savetracktoDatabase(trainingTrackDto);
	}

	@DeleteMapping("/remove/{name}")
	public String removeTraingTrack(@PathVariable String name) {
		return trainingTrackService.removeTrack(name);

	}

	@GetMapping("/get/{name}")
	public TrainingTrackDto updateTraingTrack(@PathVariable String name) {
		return trainingTrackService.getTrack(name);

	}

	@PostMapping("/all")
	public List<TrainingTrackDto> getTraingTracks() {
		return trainingTrackService.getTracks();
	}

}
