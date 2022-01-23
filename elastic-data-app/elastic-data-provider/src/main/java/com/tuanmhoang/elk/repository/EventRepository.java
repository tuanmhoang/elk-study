package com.tuanmhoang.elk.repository;

import com.tuanmhoang.elk.constant.AppConstants;
import com.tuanmhoang.elk.model.Event;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EventRepository extends ElasticsearchRepository<Event, String> {

    List<Event> findByTitle(String title);

    List<Event> findAllByTitleAndDateTimeAfter(String title, LocalDate dateTime);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"title\": \"?0\"}},{\"range\": {\"dateTime\": {\"gte\": \"?1\"} }}]}}")
    List<Event> findAllByTitleAndDateTimeAfterUsingCustom(String title, LocalDate dateTime);

}
