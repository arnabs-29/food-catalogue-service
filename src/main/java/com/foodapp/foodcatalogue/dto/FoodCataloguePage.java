package com.foodapp.foodcatalogue.dto;

import com.foodapp.foodcatalogue.entity.FoodItem;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class FoodCataloguePage {

   private List<FoodItem> foodItemList;
   private Restaurant restaurant;

   public FoodCataloguePage() {
   }

   public FoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
      this.foodItemList = foodItemList;
      this.restaurant = restaurant;
   }

   public List<FoodItem> getFoodItemList() {
      return foodItemList;
   }

   public Restaurant getRestaurant() {
      return restaurant;
   }

   public void setFoodItemList(List<FoodItem> foodItemList) {
      this.foodItemList = foodItemList;
   }

   public void setRestaurant(Restaurant restaurant) {
      this.restaurant = restaurant;
   }

   @Override
   public String toString() {
      return "FoodCataloguePage{" +
              "foodItemList=" + foodItemList +
              ", restaurant=" + restaurant +
              '}';
   }
}
