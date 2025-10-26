package com.foodapp.foodcatalogue.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class FoodItemDTO {

    private int id;
    private String itemName;
    private String itemDescription;
    private Boolean isVeg;
    private Double price;
    private Integer restaurantId;
    private Integer quantity;

    public FoodItemDTO() {
    }

    public FoodItemDTO(int id, String itemName, String itemDescription, Boolean isVeg, Double price, Integer restaurantId, Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.isVeg = isVeg;
        this.price = price;
        this.restaurantId = restaurantId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public Boolean getVeg() {
        return isVeg;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public void setVeg(Boolean veg) {
        isVeg = veg;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
