package com.thryve.backendTask.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thryve.backendTask.controller.model.output.AverageHeartRateOutput;
import com.thryve.backendTask.dao.HealthDataDao;
import com.thryve.backendTask.model.HeartRateDto;

@Service
public class HealthServiceImpl implements HealthService {

	private final HealthDataDao healthDataDao;

	@Autowired
	public HealthServiceImpl(HealthDataDao healthDataDao) {
		this.healthDataDao = healthDataDao;
	}

	@Override
	public AverageHeartRateOutput calculateAverageHeartRateForUser(final int userId) {
		List<HeartRateDto> dtos = this.healthDataDao.getHeartRatesForUser(userId);

		if (dtos == null) {
			return AverageHeartRateOutput.builder().build();
		}

		Double average = dtos.stream().mapToDouble(HeartRateDto::getValue).average().orElse(0d);

		Optional<Timestamp> start = dtos.stream().map(HeartRateDto::getStart).min((a, b) -> a.compareTo(b));
		Optional<Timestamp> end = dtos.stream().map(HeartRateDto::getEnd).max((a, b) -> a.compareTo(b));

		if (!start.isPresent() || !end.isPresent())
			return AverageHeartRateOutput.builder().build();

		return AverageHeartRateOutput.builder().withAverageHeartRate(average).withUserId(userId)
				.withTimespan(createTimespan(start.get(), end.get())).build();

	}

	protected String createTimespan(Timestamp start, Timestamp end) {
		String timespan = start.toString() + " - " + end.toString();
		return timespan;
	}

	@Override
	public void save(HeartRateDto dto) {
		healthDataDao.save(dto);
	}

	@Override
	public List<HeartRateDto> getAll() {
		return healthDataDao.getAll();
	}

	@Override
	public List<HeartRateDto> getByUser(Integer userId) {
		return healthDataDao.getHeartRatesForUser(userId);
	}
}
