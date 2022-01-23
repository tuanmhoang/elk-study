package com.tuanmhoang.elk.services;

import com.tuanmhoang.elk.model.CreateEventRequest;
import com.tuanmhoang.elk.model.Event;
import java.time.LocalDate;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface EventService {
    List<Event> getAllEvents();

    void createEvent(CreateEventRequest request);

    List<Event> searchByTitle(String title);

    List<Event> searchByTitleAndDateTimeAfter(String title, LocalDate date);
}
