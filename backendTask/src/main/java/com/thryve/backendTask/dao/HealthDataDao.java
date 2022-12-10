package com.thryve.backendTask.dao;

import com.thryve.backendTask.model.HeartRateDto;

import java.util.Collection;
import java.util.List;

public interface HealthDataDao {

    void save(HeartRateDto heartRate);

    void saveAll(Collection<HeartRateDto> heartRates);


    List<HeartRateDto> getAll();

    List<HeartRateDto> getHeartRatesForUser(int userId);

    List<HeartRateDto> getHeartRatesAboveThreshold(Long threshold);

    List<HeartRateDto> getHeartRatesBelowThreshold(Long threshold);

}
