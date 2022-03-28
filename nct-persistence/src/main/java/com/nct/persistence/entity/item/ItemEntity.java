package com.nct.persistence.entity.item;

import com.nct.domain.meal.MealType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "ITEM")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer quantity;
    private MealType mealType;
    @Basic
    @Temporal(TemporalType.DATE)
    private Date date;
    private String name;
    private Long calories;
    private Long serving_size_g;
    private Long fat_total_g;
    private Long fat_saturated_g;
    private Long protein_g;
    private Long sodium_mg;
    private Long potassium_mg;
    private Long cholesterol_mg;
    private Long carbohydrates_total_g;
    private Long fiber_g;
    private Long sugar_g;
}

