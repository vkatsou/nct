package com.nct.business.core.api.service;

import com.nct.domain.item.Item;
import reactor.core.publisher.Mono;

import java.util.List;

public interface APINinjaNutritionService {

    Mono<List<Item>> getNutrition(String name);
}
