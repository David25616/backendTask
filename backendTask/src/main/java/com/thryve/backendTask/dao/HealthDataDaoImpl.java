package com.thryve.backendTask.dao;

import com.google.common.collect.Lists;
import com.thryve.backendTask.model.EventDto;
import com.thryve.backendTask.model.HeartRateDto;
import com.thryve.backendTask.model.HeartRateEntity;
import com.thryve.backendTask.repo.DataRepository;
import com.thryve.backendTask.service.eventstore.EventPublisher;
import com.thryve.backendTask.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static com.thryve.backendTask.util.Mapper.*;

@Service
public class HealthDataDaoImpl implements HealthDataDao {


    private final DataRepository dataRepository;
    private final EventPublisher eventPublisher;



    @Autowired
    public HealthDataDaoImpl(DataRepository dataRepository, EventPublisher eventPublisher) {
        this.dataRepository = dataRepository;
        this.eventPublisher = eventPublisher;
    }


    @Override
    public void save(final HeartRateDto heartRate) {
        HeartRateEntity heartRateEntity = mapToEntity(heartRate);
        dataRepository.save(heartRateEntity);
    }

    @Transactional
    @Override
    public void saveAll(final Collection<HeartRateDto> heartRates) {
        try {
            List<HeartRateEntity> heartRateEntities = mapDtosToEntities(heartRates);
             dataRepository.saveAll(heartRateEntities);
             dataRepository.deleteAll(heartRateEntities);
            eventPublisher.publishHeartRateEvent(heartRates, EventDto.Action.INSERT, EventDto.Type.HEART_RATE);
        } catch (Exception e) {
            throw new CustomException("Error while persisting HeartRate-Data");
        }

    }

    @Override
    public List<HeartRateDto> getAll() {
        return mapEntitiesToDtos(dataRepository.findAll());
    }

    @Override
    public List<HeartRateDto> getHeartRatesForUser(final int userId) {
        return mapEntitiesToDtos(dataRepository.findByUserId(userId));
    }

    @Override
    public List<HeartRateDto> getHeartRatesAboveThreshold(final Long threshold) {
        return mapEntitiesToDtos(dataRepository.findByValueGreaterThan(threshold));
    }

    @Override
    public List<HeartRateDto> getHeartRatesBelowThreshold(final Long threshold) {
        return mapEntitiesToDtos(dataRepository.findByValueLessThan(threshold));
    }






}
