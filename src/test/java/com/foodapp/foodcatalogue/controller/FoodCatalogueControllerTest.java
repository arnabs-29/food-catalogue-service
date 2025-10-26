package com.foodapp.foodcatalogue.controller;

import com.foodapp.foodcatalogue.dto.FoodCataloguePage;
import com.foodapp.foodcatalogue.dto.FoodItemDTO;
import com.foodapp.foodcatalogue.dto.Restaurant;
import com.foodapp.foodcatalogue.entity.FoodItem;
import com.foodapp.foodcatalogue.service.FoodCatelogueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FoodCatalogueControllerTest {

    @InjectMocks
    FoodCatalogueController foodCatalogueController;

    @Mock
    FoodCatelogueService foodCatelogueService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddFoodItem(){

        FoodItemDTO mockFoodItem=new FoodItemDTO();
        mockFoodItem.setId(1);
        mockFoodItem.setItemName("Item 1");
        mockFoodItem.setItemDescription("Description 1");
        mockFoodItem.setVeg(true);
        mockFoodItem.setPrice(900.0);
        mockFoodItem.setRestaurantId(1);
        mockFoodItem.setQuantity(0);

        //mock the service behaviour
        when(foodCatelogueService.addFoodItem(mockFoodItem)).thenReturn(mockFoodItem);
        //call the controller method
        ResponseEntity<FoodItemDTO> response= foodCatalogueController.addFoodItem(mockFoodItem);
        //Verify the response
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(mockFoodItem,response.getBody());

    }

    @Test
    public void testFetchRestDetailsWithFoodMenu(){

        //Create a mock restaurant ID
        int mockRestaurantId = 1;

        //Create a mock foodcatalogue page to be returned by the service
        Restaurant mockRestaurant= new Restaurant(1,"Restaurant 1","Address 1","City 1","Description 1");
        List<FoodItem> mockFoodItems = List.of(new FoodItem(1,"Item 1","Desc 1",true,900.0,1,0),
                new FoodItem(2,"Item 2","Desc 2",false,700.0,1,0));

        FoodCataloguePage mockfoodCataloguePage=new FoodCataloguePage();
        mockfoodCataloguePage.setRestaurant(mockRestaurant);
        mockfoodCataloguePage.setFoodItemList(mockFoodItems);

        //mock the service behaviour
        when(foodCatelogueService.fetchFoodCataloguePageDetails(mockRestaurantId)).thenReturn(mockfoodCataloguePage);

        ResponseEntity<FoodCataloguePage> response=foodCatalogueController.fetchRestDetailsWithFoodMenu(mockRestaurantId);
        //verify the response
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(mockfoodCataloguePage,response.getBody());
        assertEquals(mockRestaurant,response.getBody().getRestaurant());
        assertEquals(mockFoodItems,response.getBody().getFoodItemList());

        //Verify that the service method was called
        verify(foodCatelogueService,times(1)).fetchFoodCataloguePageDetails(mockRestaurantId);
    }
}
