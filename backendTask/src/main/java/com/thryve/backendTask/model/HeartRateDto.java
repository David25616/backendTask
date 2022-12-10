package com.thryve.backendTask.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;


import java.sql.Timestamp;
import java.util.UUID;

@Value
@Builder
public class HeartRateDto {

    Timestamp start;
    Timestamp end;
    Timestamp createdAt;
    Integer dynamicValueType;
    Long value;
    Integer userId;
}
