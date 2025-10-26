package com.foodapp.foodcatalogue.service;

import com.foodapp.foodcatalogue.dto.FoodCataloguePage;
import com.foodapp.foodcatalogue.dto.FoodItemDTO;
import com.foodapp.foodcatalogue.dto.Restaurant;
import com.foodapp.foodcatalogue.entity.FoodItem;
import com.foodapp.foodcatalogue.mapper.FoodItemMapper;
import com.foodapp.foodcatalogue.repo.FoodItemRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FoodCatalogueServiceTest {

    @Mock
    private FoodItemRepo foodItemRepo;

    @Mock
    private FoodItemMapper foodItemMapper;

    @InjectMocks
    private FoodCatelogueService foodCatelogueService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddFoodItem(){
        FoodItemDTO mockFoodItemDTO=new FoodItemDTO(1,"Item 1","Description 1",true,100.0,1,0);
        FoodItem mockFoodItem= new FoodItem();
        mockFoodItem.setId(1);
        mockFoodItem.setItemName("Item 1");
        mockFoodItem.setItemDescription("Description 1");
        mockFoodItem.setVeg(true);
        mockFoodItem.setPrice(100.0);
        mockFoodItem.setRestaurantId(1);
        mockFoodItem.setQuantity(0);
        // Mock mapper behavior
        when(foodItemMapper.mapFoodItemDTOToFoodItem(mockFoodItemDTO)).thenReturn(mockFoodItem);
        when(foodItemMapper.mapFoodItemToFoodItemDTO(mockFoodItem)).thenReturn(mockFoodItemDTO);

        //Mock the repository behaviour
        when(foodItemRepo.save(mockFoodItem)).thenReturn(mockFoodItem);

        //call the service method
        FoodItemDTO savedFoodItemDTO= foodCatelogueService.addFoodItem(mockFoodItemDTO);

        //verify the result
        assertEquals(mockFoodItemDTO,savedFoodItemDTO);

        //verify that the repository method was called
        verify(foodItemRepo,times(1)).save(mockFoodItem);
        verify(foodItemMapper, times(1)).mapFoodItemDTOToFoodItem(mockFoodItemDTO);
        verify(foodItemMapper, times(1)).mapFoodItemToFoodItemDTO(mockFoodItem);
    }

    @Test
    public void testFetchFoodCataloguePageDetails(){

        int mockRestaurantId=1;
        Restaurant mockRestaurant= new Restaurant();
        mockRestaurant.setId(1);
        mockRestaurant.setName("Restaurant 1");
        mockRestaurant.setAddress("Address 1");
        mockRestaurant.setCity("City 1");
        mockRestaurant.setDescription("Description 1");

        List<FoodItem> mockFoodItems= List.of(
                new FoodItem(1,"Item 1","Description 1",true,100.0,1,0),
                new FoodItem(2,"Item 2","Description 2",false,200.0,1,0),
                new FoodItem(3,"Item 3","Description 3",true,300.0,1,0)
                );

        FoodCataloguePage mockfoodCataloguePage=new FoodCataloguePage();
        mockfoodCataloguePage.setFoodItemList(mockFoodItems);
        mockfoodCataloguePage.setRestaurant(mockRestaurant);

        when(foodItemRepo.findByRestaurantId(mockRestaurantId)).thenReturn(mockFoodItems);
        when(restTemplate.getForObject(
                "http://RESTAURANT-SERVICE/restaurant/fetchById/" + mockRestaurantId,
                Restaurant.class))
                .thenReturn(mockRestaurant);

        FoodCataloguePage response=foodCatelogueService.fetchFoodCataloguePageDetails(mockRestaurantId);

        assertEquals(response.getRestaurant().getName(),
                mockfoodCataloguePage.getRestaurant().getName());
        assertEquals(response.getRestaurant().getAddress(),
                mockfoodCataloguePage.getRestaurant().getAddress());
        assertEquals(response.getFoodItemList().size(),
                mockfoodCataloguePage.getFoodItemList().size());

        //verify that the repository method was called
        verify(foodItemRepo,times(1)).findByRestaurantId(mockRestaurantId);
        verify(restTemplate,times(1)).getForObject(
                "http://RESTAURANT-SERVICE/restaurant/fetchById/" + mockRestaurantId,
                Restaurant.class);
    }
}
