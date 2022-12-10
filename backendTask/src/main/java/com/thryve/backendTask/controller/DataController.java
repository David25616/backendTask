package com.thryve.backendTask.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thryve.backendTask.controller.model.output.AverageHeartRateOutput;
import com.thryve.backendTask.controller.model.output.HeartRateOutput;
import com.thryve.backendTask.model.HeartRateDto;
import com.thryve.backendTask.service.HealthService;

@Service
@RequestMapping(value = "/heartRates")
public class DataController {

	private final HealthService healthService;

	/* Authentication of token done on SecurityConfig class */

	@Autowired
	public DataController(HealthService healthService) {
		this.healthService = healthService;
	}

	@PostMapping(value = "/save")
	public ResponseEntity<String> saveHeartRateData(@RequestBody @Valid HeartRateDto dto) {
		healthService.save(dto);
		return new ResponseEntity<>("Health Data successfully saved", HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<HeartRateOutput>> getAll() {
		List<HeartRateDto> resultDtos = healthService.getAll();

		List<HeartRateOutput> finalResult = resultDtos.stream().map(dto -> HeartRateOutput.builder()
				.createdAt(dto.getCreatedAt()).heartRateValue(dto.getValue()).userId(dto.getUserId()).build())
				.collect(Collectors.toList());

		return ResponseEntity.ok(finalResult);
	}

	@GetMapping("/user")
	public ResponseEntity<List<HeartRateOutput>> getByUser(@RequestParam Integer userId) {
		List<HeartRateDto> resultDtos = healthService.getByUser(userId);

		List<HeartRateOutput> finalResult = resultDtos.stream().map(dto -> HeartRateOutput.builder()
				.createdAt(dto.getCreatedAt()).heartRateValue(dto.getValue()).userId(dto.getUserId()).build())
				.collect(Collectors.toList());

		return ResponseEntity.ok(finalResult);
	}

	@GetMapping("/user/average")
	public ResponseEntity<AverageHeartRateOutput> getAverageHeartRateForUser(@RequestParam Integer userId) {
		return ResponseEntity.ok(healthService.calculateAverageHeartRateForUser(userId));
	}

}
