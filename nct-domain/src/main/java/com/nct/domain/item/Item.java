package com.nct.domain.item;

import com.nct.domain.meal.MealType;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    Integer id;
    Integer quantity;
    String name;
    MealType mealType;
    Long calories;
    Long serving_size_g;
    Long fat_total_g;
    Long fat_saturated_g;
    Long protein_g;
    Long sodium_mg;
    Long potassium_mg;
    Long cholesterol_mg;
    Long carbohydrates_total_g;
    Long fiber_g;
    Long sugar_g;
}
