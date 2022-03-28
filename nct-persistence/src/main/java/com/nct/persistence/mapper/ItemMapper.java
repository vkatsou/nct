package com.nct.persistence.mapper;

import com.nct.domain.item.Item;
import com.nct.domain.nutrition.NutritionSummary;
import com.nct.persistence.entity.item.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.List;

@Mapper
public interface ItemMapper {

    ItemMapper MAPPER = Mappers.getMapper(ItemMapper.class);

    ItemEntity map(Item item);

    Item map(ItemEntity itemResource);

    List<Item> map(List<ItemEntity> item);

    default NutritionSummary mapToNutritionSummary(List<ItemEntity> items) {
        NutritionSummary nutritionSummary = new NutritionSummary();
        items.forEach(item -> {
            if (nutritionSummary.getTotalCalories()!=null){
                nutritionSummary.setTotalCalories(nutritionSummary.getTotalCalories() + item.getCalories() * item.getQuantity());
                nutritionSummary.setTotalProtein(nutritionSummary.getTotalProtein() + item.getProtein_g() * item.getQuantity());
                nutritionSummary.setTotalCarbohydrates(nutritionSummary.getTotalCarbohydrates() + item.getCarbohydrates_total_g() * item.getQuantity());
                nutritionSummary.setTotalFat(nutritionSummary.getTotalFat() + item.getFat_total_g() * item.getQuantity());
                nutritionSummary.setTotalFiber(nutritionSummary.getTotalFiber() + item.getFiber_g() * item.getQuantity());
                nutritionSummary.setTotalSugar(nutritionSummary.getTotalSugar() + item.getSugar_g() * item.getQuantity());
                nutritionSummary.setTotalSodium(nutritionSummary.getTotalSodium() + item.getSodium_mg() * item.getQuantity());
                nutritionSummary.setTotalPotassium(nutritionSummary.getTotalPotassium() + item.getPotassium_mg() * item.getQuantity());
                nutritionSummary.setTotalCholesterol(nutritionSummary.getTotalCholesterol() + item.getCholesterol_mg() * item.getQuantity());
            } else {
                nutritionSummary.setTotalCalories(item.getCalories() * item.getQuantity());
                nutritionSummary.setTotalProtein(item.getProtein_g() * item.getQuantity());
                nutritionSummary.setTotalCarbohydrates(item.getCarbohydrates_total_g() * item.getQuantity());
                nutritionSummary.setTotalFat(item.getFat_total_g() * item.getQuantity());
                nutritionSummary.setTotalFiber(item.getFiber_g() * item.getQuantity());
                nutritionSummary.setTotalSugar(item.getSugar_g() * item.getQuantity());
                nutritionSummary.setTotalSodium(item.getSodium_mg() * item.getQuantity());
                nutritionSummary.setTotalPotassium(item.getPotassium_mg() * item.getQuantity());
                nutritionSummary.setTotalCholesterol(item.getCholesterol_mg() * item.getQuantity());
            }
        });
        return nutritionSummary;
    }
}
