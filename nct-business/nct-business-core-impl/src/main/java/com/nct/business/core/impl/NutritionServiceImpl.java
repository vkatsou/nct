package com.nct.business.core.impl;

import com.nct.business.core.api.service.NutritionService;
import com.nct.domain.item.Item;
import com.nct.domain.meal.MealType;
import com.nct.domain.nutrition.NutritionSummary;
import com.nct.persistence.entity.item.ItemEntity;
import com.nct.persistence.mapper.ItemMapper;
import com.nct.persistence.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class NutritionServiceImpl implements NutritionService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public NutritionSummary getTotalNutritionValueForMealOfDate(String mealType, Date date) {
        List<ItemEntity> itemEntities;
        try {
            if (ObjectUtils.isEmpty(date)) {
                itemEntities = itemRepository.findByMealType(MealType.valueOf(mealType));
            } else {
                itemEntities = itemRepository.findByMealTypeAndDate(MealType.valueOf(mealType), date);
            }
            if (!CollectionUtils.isEmpty(itemEntities)) {
                return ItemMapper.MAPPER.mapToNutritionSummary(itemEntities);
            } else {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "You don't have any items in the specific meal to calculate calories.");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Please enter a valid mealType.");
        }
    }
}
