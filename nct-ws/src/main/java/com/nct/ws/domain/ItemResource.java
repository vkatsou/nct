package com.nct.ws.domain;

import com.nct.domain.meal.MealType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResource {
    @NotNull
    @Size(max = 50)
    private Integer id;
    @NotNull
    @Size(max = 2)
    private Integer quantity;
    @Size(max = 9)
    private MealType mealType;
    @NotNull
    @Size(max = 50)
    private String name;
    @NotNull
    @Size(max = 4)
    private Long calories;
    @NotNull
    @Size(max = 4)
    private Long serving_size_g;
    @NotNull
    @Size(max = 4)
    private Long fat_total_g;
    @NotNull
    @Size(max = 4)
    private Long fat_saturated_g;
    @NotNull
    @Size(max = 4)
    private Long protein_g;
    @NotNull
    @Size(max = 10)
    private Long sodium_mg;
    @NotNull
    @Size(max = 10)
    private Long potassium_mg;
    @NotNull
    @Size(max = 10)
    private Long cholesterol_mg;
    @NotNull
    @Size(max = 4)
    private Long carbohydrates_total_g;
    @NotNull
    @Size(max = 4)
    private Long fiber_g;
    @NotNull
    @Size(max = 4)
    private Long sugar_g;
}
