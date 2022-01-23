package com.tuanmhoang.elk.services.impl;

import com.tuanmhoang.elk.mappers.EventMapper;
import com.tuanmhoang.elk.model.CreateEventRequest;
import com.tuanmhoang.elk.model.Event;
import com.tuanmhoang.elk.repository.EventRepository;
import com.tuanmhoang.elk.services.EventService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    @Override
    public List<Event> getAllEvents() {
        return StreamSupport
            .stream(eventRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    @Override
    public void createEvent(CreateEventRequest request) {
        final Event entity = eventMapper.eventRequestToEvent(request);
        eventRepository.save(entity);
    }

    @Override
    public List<Event> searchByTitle(String title) {
        return eventRepository.findByTitle(title);
    }

    @Override
    public List<Event> searchByTitleAndDateTimeAfter(String title, LocalDate date) {
//        final List<Event> events = eventRepository.findAllByTitleAndDateTimeAfter(title, date);
        final List<Event> events = eventRepository.findAllByTitleAndDateTimeAfterUsingCustom(title, date);
        return  events;
    }
}
