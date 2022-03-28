package com.nct.ws.mapper;

import com.nct.domain.nutrition.NutritionSummary;
import com.nct.ws.domain.NutritionSummaryResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NutritionResourceMapper {

    NutritionResourceMapper MAPPER = Mappers.getMapper(NutritionResourceMapper.class);

    NutritionSummary map(NutritionSummaryResource nutritionSummaryResource);

    NutritionSummaryResource map(NutritionSummary nutritionSummary);
}
