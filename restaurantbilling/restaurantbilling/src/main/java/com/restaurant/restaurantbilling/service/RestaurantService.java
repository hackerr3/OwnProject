package com.restaurant.restaurantbilling.service;


import com.restaurant.restaurantbilling.dto.RestaurantRequestDTO;
import com.restaurant.restaurantbilling.dto.RestaurantResponseDTO;
import com.restaurant.restaurantbilling.entity.Restaurant;


import java.util.List;

public interface RestaurantService {
    List<Restaurant> getRestaurants();

    Restaurant addRestaurant(Restaurant restaurant);


    RestaurantResponseDTO addRestaurantWithoutDuplicateItems(RestaurantRequestDTO requestDTO);

    RestaurantResponseDTO addRestaurant(RestaurantRequestDTO requestDTO);

    RestaurantResponseDTO updateRestaurantWithoutDuplicateItems(Long id, RestaurantRequestDTO requestDTO);
}
