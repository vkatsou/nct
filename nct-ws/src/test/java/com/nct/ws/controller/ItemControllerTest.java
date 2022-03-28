package com.nct.ws.controller;

import com.nct.business.core.api.service.ItemService;
import com.nct.domain.item.Item;
import com.nct.domain.meal.MealType;
import com.nct.ws.mapper.ItemResourceMapper;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;



@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {

    private static final EasyRandom easyRandom = new EasyRandom();

    @InjectMocks
    ItemController itemController;

    @Mock
    ItemService itemService;

    @Test
    @DisplayName("The response of the all/{mealType} endpoint should match with the given resources")
    void getItemsByMealType() {
        Item item = easyRandom.nextObject(Item.class);
        Mockito.when(itemService.getItemsByMealType(item.getMealType().toString())).thenReturn(Collections.singletonList(item));
        Assert.isTrue(Objects.equals(itemController.getItemsByMealType(item.getMealType().toString()).getBody().get(0).getId(), ItemResourceMapper.MAPPER.map(item).getId()),
                "The item should match the one that comes from the service");
    }

    @Test
    @DisplayName("The response of ninja api should match with the given resources")
    void searchItem() {
        Item item = easyRandom.nextObject(Item.class);
        Mockito.when(itemService.searchItem(item.getName())).thenReturn(Collections.singletonList(item));
        Assert.isTrue(Objects.equals(itemController.searchItem(item.getName()).getBody().get(0).getId(), ItemResourceMapper.MAPPER.map(item).getId()),
                "The item should match the one that comes from the service");
    }

    @Test
    @DisplayName("The response of get /{id} should match with the given resources")
    void getItem() {
        Item item = easyRandom.nextObject(Item.class);
        Mockito.when(itemService.getItem(item.getId())).thenReturn(item);
        Assert.isTrue(Objects.equals(itemController.getItem(item.getId()).getBody().getId(), ItemResourceMapper.MAPPER.map(item).getId()),
                "The item should match the one that comes from the service");
    }

    @Test
    @DisplayName("With the right date you get back a the same response")
    void addItemToMeal() throws ParseException {
        String pattern = "MM.dd.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse("04.25.2021");
        Item item = easyRandom.nextObject(Item.class);
        Mockito.when(itemService.addItem(item.getName(),item.getMealType().toString(), date)).thenReturn(item);
        Assert.isTrue(Objects.equals(itemController.addItemToMeal(item.getName(), item.getMealType().toString(), date).getBody().getId(), ItemResourceMapper.MAPPER.map(item).getId()),
                "The item should match the one that comes from the service");
    }

    @Test
    @DisplayName("Delete an item from database")
    void dropItemFromMeal() {
        Item item = easyRandom.nextObject(Item.class);
        Assert.isTrue(Objects.equals(itemController.dropItemFromMeal(item.getId()).getStatusCode(), HttpStatus.OK),
                "The response should match the one that comes from the service");
    }

    @Test
    @DisplayName("Update item mealType")
    void updateItemMeal() {
        Item item = easyRandom.nextObject(Item.class);
        Item item2 = easyRandom.nextObject(Item.class);
        item2.setMealType(MealType.LUNCH);
        Mockito.when(itemService.updateItem(item.getId(), item.getMealType().toString())).thenReturn(item2);
        Assert.isTrue(Objects.equals(itemController.updateItemMeal(item.getId(), item.getMealType().toString()).getBody().getMealType(), item2.getMealType()),
                "The response should match the one that comes from the service");
    }
}