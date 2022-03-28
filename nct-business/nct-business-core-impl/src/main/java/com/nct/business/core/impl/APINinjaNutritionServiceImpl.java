package com.nct.business.core.impl;

import com.nct.business.core.api.service.APINinjaNutritionService;
import com.nct.domain.item.Item;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.List;

@Slf4j
@Service
@Data
public class APINinjaNutritionServiceImpl implements APINinjaNutritionService {

    @Autowired
    private APINinjaConfiguration apiNinjaConfiguration;
    private final WebClient webClient;

    public APINinjaNutritionServiceImpl(WebClient.Builder builder, APINinjaConfiguration apiNinjaConfiguration) {
        webClient = builder
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().followRedirect(true)
                ))
                .baseUrl(apiNinjaConfiguration.getUrl())
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public Mono<List<Item>> getNutrition(String name) {
        return webClient.get()
                .uri("/v1/nutrition?query={name}", name)
                .header(apiNinjaConfiguration.getHeader(), apiNinjaConfiguration.getApiKey())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Item>>() {});
    }

}
