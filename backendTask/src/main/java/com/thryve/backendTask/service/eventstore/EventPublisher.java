package com.thryve.backendTask.service.eventstore;

import com.thryve.backendTask.model.EventDto;
import com.thryve.backendTask.model.HeartRateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EventPublisher {


    private final ApplicationEventPublisher publisher;

    @Autowired
    public EventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }


    public void publishHeartRateEvent(final Collection<HeartRateDto> heartRateDtos,
                                      final EventDto.Action eventAction,
                                      final EventDto.Type type) {
        Set<Integer> userIds = heartRateDtos.stream().map(HeartRateDto::getUserId).collect(Collectors.toSet());
        userIds.forEach(userId -> {
            EventDto eventDto = EventDto.builder().type(type).action(eventAction)
                    .created(new Timestamp(new Date().getTime()))
                    .userId(userId)
                    .build();
            publisher.publishEvent(eventDto);
        });
    }
}
