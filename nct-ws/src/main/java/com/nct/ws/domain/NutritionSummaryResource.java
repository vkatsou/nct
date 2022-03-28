package com.nct.ws.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NutritionSummaryResource {
    private Long totalCalories;
    private Long totalProtein;
    private Long totalCarbohydrates;
    private Long totalFat;
    private Long totalFiber;
    private Long totalSugar;
    private Long totalSodium;
    private Long totalPotassium;
    private Long totalCholesterol;
}