package com.thryve.backendTask.controller.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSources {

    private Integer dataSource;
    @JsonProperty(value = "data")
    private List<HealthData> healthData;
}
