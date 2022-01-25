package com.tuanmhoang.elk.controller;

import com.tuanmhoang.elk.constant.AppConstants;
import com.tuanmhoang.elk.model.CreateEventRequest;
import com.tuanmhoang.elk.model.Event;
import com.tuanmhoang.elk.services.EventService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/events")
public interface EventController {

    @GetMapping
    ResponseEntity<List<Event>> getAllEvents();

    @PostMapping
    void createEvent(@RequestBody CreateEventRequest request);

    @GetMapping("/title/{title}")
    ResponseEntity<List<Event>> searchByTitle(@PathVariable String title);

    @GetMapping("/title/{title}/after/{date}")
    ResponseEntity<List<Event>> searchByTitleAndDateTimeAfter(@PathVariable String title,
        @PathVariable @DateTimeFormat(pattern = AppConstants.YYYY_MM_DD) LocalDate date);

}
