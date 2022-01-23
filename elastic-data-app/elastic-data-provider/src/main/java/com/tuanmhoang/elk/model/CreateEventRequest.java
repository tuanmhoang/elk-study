package com.tuanmhoang.elk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.tuanmhoang.elk.constant.AppConstants;
import com.tuanmhoang.elk.constant.EventType;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
