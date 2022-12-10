package com.thryve.backendTask.service;

import com.thryve.backendTask.controller.model.output.AverageHeartRateOutput;

public interface HealthService {

    AverageHeartRateOutput calculateAverageHeartRateForUser(int userId);

}
