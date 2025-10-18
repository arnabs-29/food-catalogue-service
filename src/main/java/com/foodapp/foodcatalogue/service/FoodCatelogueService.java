package com.foodapp.foodcatalogue.service;

import com.foodapp.foodcatalogue.dto.FoodCataloguePage;
import com.foodapp.foodcatalogue.dto.FoodItemDTO;
import com.foodapp.foodcatalogue.dto.Restaurant;
import com.foodapp.foodcatalogue.entity.FoodItem;
import com.foodapp.foodcatalogue.mapper.FoodItemMapper;
import com.foodapp.foodcatalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatelogueService {

    @Autowired
    private FoodItemRepo foodItemRepo;

    @Autowired
    private RestTemplate restTemplate;

    private FoodItemMapper foodItemMapper;

    public FoodCatelogueService(FoodItemMapper foodItemMapper) {
        this.foodItemMapper = foodItemMapper;
    }
    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO){
        FoodItem foodItem=foodItemRepo.save(foodItemMapper.mapFoodItemDTOToFoodItem(foodItemDTO));
        return foodItemMapper.mapFoodItemToFoodItemDTO(foodItem);
    }

    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId){
        List<FoodItem> foodItemList=fetchFoodItemList(restaurantId);
        Restaurant restaurant= fetchRestaurantDetailsFromRestaurantMS(restaurantId);
        return createFoodCataloguePage(foodItemList,restaurant);
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
        FoodCataloguePage foodCataloguePage=new FoodCataloguePage();
        foodCataloguePage.setFoodItemList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
    }

    private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
        Restaurant restaurant = restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantId,Restaurant.class);
        return restaurant;
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }
}
