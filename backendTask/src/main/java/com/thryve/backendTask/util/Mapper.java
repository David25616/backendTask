package com.thryve.backendTask.util;

import com.thryve.backendTask.controller.model.output.HeartRateOutput;
import com.thryve.backendTask.model.EventDto;
import com.thryve.backendTask.model.HeartRateDto;
import com.thryve.backendTask.model.HeartRateEntity;
import com.thryve.backendTask.repo.entity.EventEntity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    public static HeartRateDto mapToDto(final HeartRateEntity heartRateEntity) {
        return HeartRateDto.builder()
                .start(heartRateEntity.getStartTimestamp())
                .end(heartRateEntity.getEndTimestamp())
                .createdAt(heartRateEntity.getCreatedAt())
                .dynamicValueType(heartRateEntity.getDynamicValueType())
                .value(heartRateEntity.getValue())
                .userId(heartRateEntity.getUserId()).build();
    }

    public static HeartRateEntity mapToEntity(final HeartRateDto heartRateDto) {
        return new HeartRateEntity(null, heartRateDto.getStart(),
                heartRateDto.getEnd(),
                heartRateDto.getCreatedAt(),
                heartRateDto.getDynamicValueType(),
                heartRateDto.getValue(),
                heartRateDto.getUserId());
    }

    public static List<HeartRateEntity> mapDtosToEntities(final Collection<HeartRateDto> heartRateDtos) {
        return heartRateDtos
                .stream()
                .map(heartRateDto -> mapToEntity(heartRateDto))
                .collect(Collectors.toList());
    }

    public static List<HeartRateDto> mapEntitiesToDtos(final List<HeartRateEntity> heartRateEntities) {
        return heartRateEntities
                .stream()
                .map(hre -> mapToDto(hre))
                .collect(Collectors.toList());
    }

    public static List<HeartRateOutput> mapDtosToHeartRatesOutput(final List<HeartRateDto> heartRateDtos) {
        return heartRateDtos.stream()
                .map(heartRateDto -> Mapper.mapDtoToHeartRateOutput(heartRateDto))
                .collect(Collectors.toList());
    }

    public static HeartRateOutput mapDtoToHeartRateOutput(final HeartRateDto heartRateDto) {
        return HeartRateOutput.builder()
                .heartRateValue(heartRateDto.getValue())
                .createdAt(heartRateDto.getCreatedAt()).userId(heartRateDto.getUserId())
                .build();
    }

    public static EventEntity mapEventDtoToEventEntity(final EventDto eventDto) {
        EventEntity entity = new EventEntity();
        entity.setAction(eventDto.getAction().name());
        entity.setType(eventDto.getType().name());
        entity.setUserId(eventDto.getUserId());
        entity.setCreated(eventDto.getCreated());
        return entity;
    }
}
