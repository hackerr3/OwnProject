package com.restaurant.restaurantbilling.dto;

import lombok.Data;

import java.util.*;

@Data
public class RestaurantResponseDTO {
    private String restaurantId; // Randomly generated UUID
    private String restaurantName;
    private String restaurantAddress;
    private List<MenuItemDTO> menuItems;
}
