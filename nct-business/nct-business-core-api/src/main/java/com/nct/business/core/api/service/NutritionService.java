package com.nct.business.core.api.service;


import com.nct.domain.nutrition.NutritionSummary;

import java.util.Date;

public interface NutritionService {

    NutritionSummary getTotalNutritionValueForMealOfDate(String mealType, Date date);

}
