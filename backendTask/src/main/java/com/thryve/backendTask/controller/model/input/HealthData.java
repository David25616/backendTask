package com.thryve.backendTask.controller.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthData {

    private Long startTimestampUnix;
    private Long endTimestampUnix;
    private Long createdAtUnix;
    private Integer dynamicValueType;
    private String value;
    private String valueType;
}
