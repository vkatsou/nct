package com.nct.business.core.api.service;

import com.nct.domain.item.Item;
import com.nct.domain.meal.MealType;

import java.util.Date;
import java.util.List;

public interface ItemService {

    List<Item> getItemsByMealType(String name);

    List<Item> searchItem(String name);

    Item getItem(Integer id);

    Item addItem(String name, String mealType, Date date);

    void dropItem(Integer id);

    Item updateItem(Integer id, String mealType);
}
