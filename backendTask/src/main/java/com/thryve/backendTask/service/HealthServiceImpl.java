package com.thryve.backendTask.service;

import com.thryve.backendTask.controller.model.output.AverageHeartRateOutput;
import com.thryve.backendTask.dao.HealthDataDao;
import com.thryve.backendTask.model.HeartRateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;

@Service
public class HealthServiceImpl implements HealthService {

    private final HealthDataDao healthDataDao;

    @Autowired
    public HealthServiceImpl(HealthDataDao healthDataDao) {
        this.healthDataDao = healthDataDao;
    }

    @Override
    public AverageHeartRateOutput calculateAverageHeartRateForUser(final int userId) {
    }

    protected String createTimespan(Timestamp start, Timestamp end) {
        String timespan = start.toString() + " - " + end.toString();
        return timespan;
    }
}
