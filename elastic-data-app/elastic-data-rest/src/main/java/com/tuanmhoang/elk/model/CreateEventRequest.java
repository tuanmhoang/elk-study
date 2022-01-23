package com.tuanmhoang.elk.model;

import com.tuanmhoang.elk.constant.EventType;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class CreateEventRequest {

    private String title;

    private EventType eventType;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = AppConstants.YYYY_MM_DD)

    private LocalDate dateTime;

    private String place;

    private String description;

    private List<SubTopic> subTopics;
}
