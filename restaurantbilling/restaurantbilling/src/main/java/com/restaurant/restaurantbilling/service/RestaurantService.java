package com.restaurant.restaurantbilling.service;

import com.restaurant.restaurantbilling.dao.RestaurantRequest;
import com.restaurant.restaurantbilling.entity.Restaurant;
import jakarta.transaction.Transactional;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> getRestaurants();

    Restaurant addRestaurant(Restaurant restaurant);

    @Transactional
    Restaurant addRestaurantWithMenu(RestaurantRequest restaurantRequest);
}
