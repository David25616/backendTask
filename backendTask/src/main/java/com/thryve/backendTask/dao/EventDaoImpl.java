package com.thryve.backendTask.dao;

import com.thryve.backendTask.model.EventDto;
import com.thryve.backendTask.repo.EventRepository;
import com.thryve.backendTask.repo.entity.EventEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.thryve.backendTask.util.Mapper.mapEventDtoToEventEntity;

@Service
public class EventDaoImpl implements EventDao {

    private final EventRepository eventRepository;

    @Autowired
    public EventDaoImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public void save(final EventDto eventDto) {
        EventEntity entity = mapEventDtoToEventEntity(eventDto);

    }
}
