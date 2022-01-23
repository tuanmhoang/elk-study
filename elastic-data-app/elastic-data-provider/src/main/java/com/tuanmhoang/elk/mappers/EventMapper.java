package com.tuanmhoang.elk.mappers;

import com.tuanmhoang.elk.model.CreateEventRequest;
import com.tuanmhoang.elk.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(target = "id", ignore = true)
    Event eventRequestToEvent(CreateEventRequest request);
}
