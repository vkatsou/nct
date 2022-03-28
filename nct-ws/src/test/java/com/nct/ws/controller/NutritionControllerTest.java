package com.nct.ws.controller;

import com.nct.business.core.api.service.ItemService;
import com.nct.business.core.api.service.NutritionService;
import com.nct.domain.item.Item;
import com.nct.domain.meal.MealType;
import com.nct.domain.nutrition.NutritionSummary;
import com.nct.ws.mapper.ItemResourceMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
public class NutritionControllerTest {

    private static final EasyRandom easyRandom = new EasyRandom();

    @InjectMocks
    NutritionController nutritionController;

    @Mock
    NutritionService nutritionService;

    @Test
    @DisplayName("The response of the nutrition endpoint should match with the given resources")
    void getItem() {
        NutritionSummary nutrition = easyRandom.nextObject(NutritionSummary.class);
        Mockito.when(nutritionService.getTotalNutritionValueForMealOfDate(MealType.BREAKFAST.toString(), null)).thenReturn(nutrition);
        Assert.isTrue(Objects.equals(nutritionController.getItem(MealType.BREAKFAST.toString(), null).getBody().getTotalCalories(), nutrition.getTotalCalories()),
                "The nutrition should match the one that comes from the service");
    }
}
