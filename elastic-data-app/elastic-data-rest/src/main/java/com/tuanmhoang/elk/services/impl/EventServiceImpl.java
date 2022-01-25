package com.tuanmhoang.elk.services.impl;

import com.tuanmhoang.elk.constant.Action;
import com.tuanmhoang.elk.constant.AppConstants;
import com.tuanmhoang.elk.mapper.ElasticSearchResultMapper;
import com.tuanmhoang.elk.model.CreateEventRequest;
import com.tuanmhoang.elk.model.Event;
import com.tuanmhoang.elk.services.EventService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class EventServiceImpl implements EventService {

    private static final String SEARCH_ENDPOINT = "/_search";

    @Value("${elk.indexName}")
    private String indexName;

    private final RestClient restClient;

    private final ElasticSearchResultMapper mapper;

    @Override
    public List<Event> getAllEvents() {
        log.info("Getting all events");
        Request searchRequest = new Request("GET", indexName + SEARCH_ENDPOINT);
        return performRequest(searchRequest);
    }

    @Override
    public void createEvent(CreateEventRequest request) {

    }

    @Override
    public List<Event> searchByTitle(String title) {
        log.info("Search events by title {}", title);
        Request searchRequest = new Request(HttpMethod.GET.name(), indexName + SEARCH_ENDPOINT);
        searchRequest.addParameter("q", "title:" + title);
        return performRequest(searchRequest);
    }

    @Override
    public List<Event> searchByTitleAndDateTimeAfter(String title, LocalDate date) {
        log.info("Search events by title {} and date after {}", title, date);
        Request searchRequest = new Request(HttpMethod.POST.name(), indexName + SEARCH_ENDPOINT);
        String queryBody = String.format(AppConstants.ADVANCED_QUERY, title, date);
        searchRequest.setJsonEntity(queryBody);
        return performRequest(searchRequest);
    }

    private List<Event> performRequest(Request searchRequest) {
        try {
            Response response = restClient.performRequest(searchRequest);
            String content = EntityUtils.toString(response.getEntity());
            return mapper.convert(content);
        } catch (IOException e) {
            throw new RuntimeException("Error during client request", e);
        }
    }
}
