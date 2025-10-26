package com.foodapp.foodcatalogue.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String itemName;
    private String itemDescription;
    private Boolean isVeg;
    private Double price;
    private Integer restaurantId;
    @Column(nullable = false,columnDefinition = "INT DEFAULT 0")
    private Integer quantity;

    public FoodItem() {
    }

    public FoodItem(int id, String itemName, String itemDescription, Boolean isVeg, Double price, Integer restaurantId, Integer quantity) {
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

    @Override
    public String toString() {
        return "FoodItem{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", isVeg=" + isVeg +
                ", price=" + price +
                ", restaurantId=" + restaurantId +
                ", quantity=" + quantity +
                '}';
    }
}
