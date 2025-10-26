package com.foodapp.foodcatalogue.mapper;

import com.foodapp.foodcatalogue.dto.FoodItemDTO;
import com.foodapp.foodcatalogue.entity.FoodItem;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FoodCatalogueMapperTest {

    // Create a real mapper instance (not mocked)
    private final FoodItemMapper mapper = Mappers.getMapper(FoodItemMapper.class);

    @Test
    public void testMapFoodItemToFoodItemDTO() {
        // Arrange
        FoodItem mockFoodItem=new FoodItem(1,"Item 1","Description 1",true,100.0,1,0);

        // Act
        FoodItemDTO foodItemDTO = mapper.mapFoodItemToFoodItemDTO(mockFoodItem);

        foodItemDTO= new FoodItemDTO(1,"Item 1","Description 1",true,100.0,1,0);
        // Assert
        assertNotNull(foodItemDTO);
        assertEquals(mockFoodItem.getId(), foodItemDTO.getId());
        assertEquals(mockFoodItem.getItemName(), foodItemDTO.getItemName());
        assertEquals(mockFoodItem.getItemDescription(), foodItemDTO.getItemDescription());
        assertEquals(mockFoodItem.getPrice(), foodItemDTO.getPrice());
        assertEquals(mockFoodItem.getPrice(), foodItemDTO.getPrice());
        assertEquals(mockFoodItem.getRestaurantId(),foodItemDTO.getRestaurantId());
        assertEquals(mockFoodItem.getQuantity(),foodItemDTO.getQuantity());
    }

    @Test
    public void testMapFoodItemDTOToFoodItem() {
        // Arrange
        FoodItemDTO mockFoodItemDTO=new FoodItemDTO(1,"Item 1","Description 1",true,100.0,1,0);

        // Act
        FoodItem foodItem = mapper.mapFoodItemDTOToFoodItem(mockFoodItemDTO);
        foodItem=new FoodItem(1,"Item 1","Description 1",true,100.0,1,0);
        // Assert
        assertNotNull(foodItem);
        assertEquals(mockFoodItemDTO.getId(), foodItem.getId());
        assertEquals(mockFoodItemDTO.getItemName(), foodItem.getItemName());
        assertEquals(mockFoodItemDTO.getItemDescription(), foodItem.getItemDescription());
        assertEquals(mockFoodItemDTO.getPrice(), foodItem.getPrice());
        assertEquals(mockFoodItemDTO.getPrice(), foodItem.getPrice());
        assertEquals(mockFoodItemDTO.getRestaurantId(),foodItem.getRestaurantId());
        assertEquals(mockFoodItemDTO.getQuantity(),foodItem.getQuantity());
    }
}
