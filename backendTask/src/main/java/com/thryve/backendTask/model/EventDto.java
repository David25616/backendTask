package com.thryve.backendTask.model;

import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;

@Value
@Builder
public class EventDto {

    Integer userId;
    Timestamp created;
    Action action;
    Type type;



    public enum Action {
        INSERT,
        UPDATE,
        DELETE
    }

    public enum Type {
        HEART_RATE,
        BLOOD_PRESSURE,
        WEIGHT,
        HEIGHT
    }


}
