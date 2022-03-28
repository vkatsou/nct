package com.nct.business.core.impl;

import com.nct.business.core.api.service.APINinjaNutritionService;
import com.nct.business.core.api.service.ItemService;
import com.nct.domain.item.Item;
import com.nct.domain.meal.MealType;
import com.nct.persistence.entity.item.ItemEntity;
import com.nct.persistence.mapper.ItemMapper;
import com.nct.persistence.repository.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    APINinjaNutritionService apiNinjaNutritionService;

    @Override
    public List<Item> getItemsByMealType(String mealType) {
        try {
            return ItemMapper.MAPPER.map(itemRepository.findByMealType(MealType.valueOf(mealType)));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Please input a valid meal type", e);
        }
    }

    @Override
    public List<Item> searchItem(String name) {
        try {
            return apiNinjaNutritionService.getNutrition(name).block();
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "The item you requested was not found in Ninja nutrition api", e);
        }
    }

    @Override
    public Item getItem(Integer id) {
        try {
            return ItemMapper.MAPPER.map(itemRepository.findById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "The item you requested was not found in Ninja nutrition api", e);
        }
    }

    @Override
    public Item addItem(String name, String mealType, Date date) {
        try {
            ItemEntity foundItemEntity = itemRepository.findByMealTypeAndDateAndName(MealType.valueOf(mealType), ObjectUtils.isEmpty(date) ? new Date() : date, name);
            if (foundItemEntity == null) {
                Item item = apiNinjaNutritionService.getNutrition(name).block().get(0);
                ItemEntity itemEntity = ItemMapper.MAPPER.map(item);
                itemEntity.setMealType(MealType.valueOf(mealType));
                itemEntity.setQuantity(1);
                itemEntity.setDate(new Date());
                itemRepository.saveAndFlush(itemEntity);
                return ItemMapper.MAPPER.map(itemEntity);
            } else {
                foundItemEntity.setQuantity(foundItemEntity.getQuantity() + 1);
                itemRepository.saveAndFlush(foundItemEntity);
                return ItemMapper.MAPPER.map(foundItemEntity);
            }
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Please specify a correct meal type according to the README.md file.", e);
        }
    }

    @Override
    public void dropItem(Integer id) {
        try {
            ItemEntity itemEntity = itemRepository.findById(id);
            itemRepository.delete(itemEntity);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cannot find the requested item with id: " + id, e);
        }
    }

    @Override
    public Item updateItem(Integer id, String mealType) {
        try {
            ItemEntity foundItemEntity = itemRepository.findById(id);
            if (foundItemEntity != null) {
                foundItemEntity.setMealType(MealType.valueOf(mealType));
                itemRepository.saveAndFlush(foundItemEntity);
                return ItemMapper.MAPPER.map(foundItemEntity);
            } else {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "The item doesn't belong to this meal type.");
            }
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Something went wrong.", e);
        }
    }
}