package com.thryve.backendTask.controller.model.output;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(setterPrefix = "with")
@JsonDeserialize(builder = AverageHeartRateOutput.AverageHeartRateOutputBuilder.class)
public class AverageHeartRateOutput {

    Integer userId;
    Double averageHeartRate;
    String timespan;


}
