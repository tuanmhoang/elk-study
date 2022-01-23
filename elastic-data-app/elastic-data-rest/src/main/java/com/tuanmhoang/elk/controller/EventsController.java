package com.tuanmhoang.elk.controller;

import com.tuanmhoang.elk.constant.AppConstants;
import com.tuanmhoang.elk.model.CreateEventRequest;
import com.tuanmhoang.elk.model.Event;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/events")
public interface EventsController {

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents();

    @PostMapping
    public void createEvent(@RequestBody CreateEventRequest request);

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Event>> searchByTitle(@PathVariable String title);

    @GetMapping("/title/{title}/after/{date}")
    public ResponseEntity<List<Event>> searchByTitleAndDateTimeAfter(@PathVariable String title,
        @PathVariable @DateTimeFormat(pattern = AppConstants.YYYY_MM_DD) LocalDate date);
}
