package com.tuanmhoang.elk.controller.impl;

import com.tuanmhoang.elk.controller.EventsController;
import com.tuanmhoang.elk.model.CreateEventRequest;
import com.tuanmhoang.elk.model.Event;
import com.tuanmhoang.elk.services.EventService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventsControllerImpl implements EventsController {

    private final EventService eventService;

    @Override
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @Override
    public void createEvent(CreateEventRequest request) {
        eventService.createEvent(request);
    }

    @Override
    public ResponseEntity<List<Event>> searchByTitle(String title) {
        return ResponseEntity.ok(eventService.searchByTitle(title));
    }

    @Override
    public ResponseEntity<List<Event>> searchByTitleAndDateTimeAfter(String title, LocalDate date) {
        return ResponseEntity.ok(eventService.searchByTitleAndDateTimeAfter(title, date));
    }
}
