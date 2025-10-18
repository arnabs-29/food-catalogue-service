package com.foodapp.foodcatalogue.dto;

import com.foodapp.foodcatalogue.entity.FoodItem;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodCataloguePage {

   private List<FoodItem> foodItemList;
   private Restaurant restaurant;
}
