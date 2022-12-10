package com.thryve.backendTask.service;

import java.util.List;

import com.thryve.backendTask.controller.model.output.AverageHeartRateOutput;
import com.thryve.backendTask.model.HeartRateDto;

public interface HealthService {

	AverageHeartRateOutput calculateAverageHeartRateForUser(int userId);

	void save(HeartRateDto dto);

	List<HeartRateDto> getAll();

	List<HeartRateDto> getByUser(Integer userId);

}
