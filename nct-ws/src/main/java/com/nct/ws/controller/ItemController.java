package com.nct.ws.controller;

import com.nct.business.core.api.service.ItemService;
import com.nct.ws.domain.ItemResource;
import com.nct.ws.mapper.ItemResourceMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Data
@RestController
@AllArgsConstructor
@NoArgsConstructor
@Tag(name = "Items", description = "Search, add, remove, get items to your daily meal.")
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * Get all items by given meal type.
     * @param mealType the mealType
     * @return item list
     */
    @GetMapping(value = "all/{mealType}")
    public ResponseEntity<List<ItemResource>> getItemsByMealType(@PathVariable String mealType){
        return ResponseEntity.ok(ItemResourceMapper.MAPPER.map(itemService.getItemsByMealType(mealType)));
    }

    /**
     * Search item's nutritional value.
     * @param name item name
     * @return list of potential items that matches the search definition.
     */
    @GetMapping(value = "search/{name}")
    public ResponseEntity<List<ItemResource>> searchItem(@PathVariable String name){
        return ResponseEntity.ok(ItemResourceMapper.MAPPER.map(itemService.searchItem(name)));
    }

    /**
     * Get a stored item from database.
     * @param id item identifier
     * @return the item with the specific identifier
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ItemResource> getItem(@PathVariable Integer id){
        return ResponseEntity.ok(ItemResourceMapper.MAPPER.map(itemService.getItem(id)));
    }

    /**
     * Add an item to a specific meal.
     * @param name item name
     * @param mealType the meal type
     * @param date the date of the meal
     * @return the successfully added item
     */
    @PostMapping(value = "/{name}")
    public ResponseEntity<ItemResource> addItemToMeal(@PathVariable String name, @RequestParam String mealType, @RequestParam(required = false) @DateTimeFormat(pattern = "MM.dd.yyyy") Date date){
        return ResponseEntity.ok(ItemResourceMapper.MAPPER.map(itemService.addItem(name, mealType, date)));
    }

    /**
     * Delete an item from a meal.
     * @param id item identifier
     * @return response status
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> dropItemFromMeal(@PathVariable Integer id){
        itemService.dropItem(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Update the item's meal type
     * @param id item identifier
     * @param mealType type of meal
     * @return item corresponding to the parameters
     */
    @PatchMapping(value = "/{id}")
    public ResponseEntity<ItemResource> updateItemMeal(@PathVariable Integer id, @RequestParam String mealType){
        return ResponseEntity.ok(ItemResourceMapper.MAPPER.map(itemService.updateItem(id, mealType)));
    }
}
