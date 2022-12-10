package com.thryve.backendTask.controller;

import com.thryve.backendTask.controller.model.output.AverageHeartRateOutput;
import com.thryve.backendTask.controller.model.output.HeartRateOutput;
import com.thryve.backendTask.dao.HealthDataDao;
import com.thryve.backendTask.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequestMapping(value = "/heartRates")
public class DataController {

    private final HealthService healthService;
    private final HealthDataDao healthDataDao;
    private final AuthTokenValidator authTokenValidator;

    @Autowired
    public DataController(HealthService healthService,
                          HealthDataDao healthDataDao,
                          AuthTokenValidator authTokenValidator) {
        this.healthService = healthService;
        this.healthDataDao = healthDataDao;
        this.authTokenValidator = authTokenValidator;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<String> saveHeartRateData(){

        return new ResponseEntity<>("Health Data successfully saved", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<HeartRateOutput>> getAll() {
    }

    @GetMapping("/user")
    public ResponseEntity<List<HeartRateOutput>> getByUser(@RequestParam Integer userId) {
    }


    @GetMapping("/user/average")
    public ResponseEntity<AverageHeartRateOutput> getAverageHeartRateForUser(@RequestParam Integer userId) {
    }


}
