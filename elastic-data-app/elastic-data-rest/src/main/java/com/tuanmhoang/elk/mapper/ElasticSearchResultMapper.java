package com.tuanmhoang.elk.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.tuanmhoang.elk.model.Event;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ElasticSearchResultMapper {

    private final ObjectMapper mapper;

    public List<Event> convert(String content) {
        List<Event> events = new ArrayList<>();
        try {
            JsonNode json = mapper.readTree(content);

            ArrayNode results = (ArrayNode) json.get("hits").get("hits");
            for (int i = 0; i < results.size(); i++) {
                String id = results.get(i).get("_id").asText();
                JsonNode eventSource = results.get(i).get("_source");
                Event event = mapper.convertValue(eventSource, Event.class);
                event.setId(id);
                events.add(event);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't parse response from Elastic ", e);
        }
        return events;
    }
}