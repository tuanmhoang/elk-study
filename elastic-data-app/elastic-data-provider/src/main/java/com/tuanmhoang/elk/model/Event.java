package com.tuanmhoang.elk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tuanmhoang.elk.constant.EventType;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "events")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {
    private String id;

    private String title;

    private EventType eventType;

    // according to: https://spring.io/blog/2020/05/27/what-s-new-in-spring-data-elasticsearch-4-0 this should work??
    @Field( type = FieldType.Date, store = true, format = DateFormat.date)
    private LocalDate dateTime;

    private String place;

    private String description;

    private List<SubTopic> subTopics;
}
