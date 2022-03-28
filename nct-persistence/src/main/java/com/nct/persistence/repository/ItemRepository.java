package com.nct.persistence.repository;

import com.nct.domain.meal.MealType;
import com.nct.persistence.entity.item.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long>, JpaSpecificationExecutor<ItemEntity> {
    ItemEntity findById(Integer name);
    List<ItemEntity> findByMealType(MealType name);
    List<ItemEntity> findByMealTypeAndDate(MealType mealType, Date date);
    ItemEntity findByMealTypeAndDateAndName(MealType mealType, Date date, String name);
}
