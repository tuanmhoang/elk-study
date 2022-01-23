package com.tuanmhoang.elk.configuration;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Value("${elk.host}")
    private String elasticHost;

    @Value("${elk.protocol}")
    private String elasticProtocol;

    @Value("${elk.port}")
    private int elasticPort;

    @Bean
    public RestClient restClient() {
        RestClientBuilder builder = RestClient.builder(
            new HttpHost(elasticHost, elasticPort, elasticProtocol)
        );
        return builder.build();
    }
}
