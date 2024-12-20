package com.restaurant.restaurantbilling.dto;

import lombok.*;

import java.util.*;

@Data
public class RestaurantRequestDTO {
    private String restaurantName;
    private String restaurantAddress;
    private List<MenuItemDTO> menuItems;
}