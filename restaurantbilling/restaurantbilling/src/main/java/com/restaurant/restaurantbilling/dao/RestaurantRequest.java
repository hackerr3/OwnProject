package com.restaurant.restaurantbilling.dao;

import lombok.*;

import java.util.*;

@Data
public class RestaurantRequest {
    private String name;
    private String address;
    private List<MenuItemRequest> menuItems;
}
