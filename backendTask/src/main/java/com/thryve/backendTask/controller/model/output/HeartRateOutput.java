package com.thryve.backendTask.controller.model.output;

import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;

@Builder
@Value
public class HeartRateOutput {

    Timestamp createdAt;
    Long heartRateValue;
    Integer userId;
}
