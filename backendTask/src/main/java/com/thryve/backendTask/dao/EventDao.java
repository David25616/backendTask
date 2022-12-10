package com.thryve.backendTask.dao;

import com.thryve.backendTask.model.EventDto;

public interface EventDao {

    void save(EventDto eventDto);
}
