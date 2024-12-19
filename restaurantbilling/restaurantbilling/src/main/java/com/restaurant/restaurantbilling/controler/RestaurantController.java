package com.restaurant.restaurantbilling.controler;

import com.restaurant.restaurantbilling.dao.RestaurantRequest;
import com.restaurant.restaurantbilling.entity.Restaurant;
import com.restaurant.restaurantbilling.service.RestaurantService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getRestaurants() {
        return restaurantService.getRestaurants();
    }

    @PostMapping
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }

 /*   @PostMapping
    public Restaurant addRestaurant(@RequestBody RestaurantRequest restaurantRequest) {
        if (restaurantRequest.getName() == null || restaurantRequest.getName().isEmpty()) {
            throw new IllegalArgumentException("Restaurant name is mandatory.");
        }
        if (restaurantRequest.getAddress() == null || restaurantRequest.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Restaurant address is mandatory.");
        }
        if (restaurantRequest.getMenuItems() == null || restaurantRequest.getMenuItems().isEmpty()) {
            throw new IllegalArgumentException("At least one menu item is required.");
        }
        return restaurantService.addRestaurantWithMenu(restaurantRequest);
    }*/
}