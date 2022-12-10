package com.thryve.backendTask.service.eventstore;

import com.thryve.backendTask.dao.EventDao;
import com.thryve.backendTask.model.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventReceiver {

    private final EventDao eventDao;

    @Autowired
    public EventReceiver(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    @EventListener
    public void receiveHeartRateEvent(final EventDto eventDto) {
        eventDao.save(eventDto);
    }
}
