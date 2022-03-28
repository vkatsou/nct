package com.nct.ws.controller;

import com.nct.business.core.api.service.NutritionService;
import com.nct.domain.nutrition.NutritionSummary;
import com.nct.ws.domain.NutritionSummaryResource;
import com.nct.ws.mapper.NutritionResourceMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Data
@RestController
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Nutrition", description = "Find the total calories of your meals.")
@RequestMapping(value = "/nutrition")
public class NutritionController {

    @Autowired
    private NutritionService nutritionService;

    /**
     * Get the total nutritional value of a specific meal of a day.
     * @param mealType the type of meal
     * @param date the date
     * @return nutrition summary
     */
    @GetMapping(value = "/{mealType}")
    public ResponseEntity<NutritionSummaryResource> getItem(@PathVariable String mealType, @RequestParam(required = false) @DateTimeFormat(pattern = "MM.dd.yyyy") Date date){
        NutritionSummary nutritionSummary = nutritionService.getTotalNutritionValueForMealOfDate(mealType, date);
        return ResponseEntity.ok(NutritionResourceMapper.MAPPER.map(nutritionSummary));
    }
}
