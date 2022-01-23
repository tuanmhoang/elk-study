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
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private static final String SEARCH_ENDPOINT = "/_search";

    @Value("${elk.indexName}")
    private String indexName;

    private final RestClient restClient;

    private final ElasticSearchResultMapper mapper;

    @Override
    public List<Event> getAllEvents() {
        Request searchRequest = new Request("GET", indexName + Action.SEARCH.getType());
        return performRequest(searchRequest);
    }

    @Override
    public void createEvent(CreateEventRequest request) {

    }

    @Override
    public List<Event> searchByTitle(String title) {
        Request searchRequest = new Request(HttpMethod.GET.name(), indexName + Action.SEARCH.getType());
        searchRequest.addParameter("q", "title:" + title);
        return performRequest(searchRequest);
    }

    @Override
    public List<Event> searchByTitleAndDateTimeAfter(String title, LocalDate date) {
        Request searchRequest = new Request(HttpMethod.POST.name(), indexName + Action.SEARCH.getType());
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
