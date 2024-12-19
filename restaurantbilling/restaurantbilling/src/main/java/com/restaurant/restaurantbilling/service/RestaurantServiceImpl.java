package com.restaurant.restaurantbilling.service;

import com.restaurant.restaurantbilling.dao.MenuItemRequest;
import com.restaurant.restaurantbilling.dao.RestaurantRequest;
import com.restaurant.restaurantbilling.entity.MenuItem;
import com.restaurant.restaurantbilling.entity.Restaurant;
import com.restaurant.restaurantbilling.repo.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    @Override
    public Restaurant addRestaurantWithMenu(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(restaurantRequest.getName());
        restaurant.setRestaurantAddress(restaurantRequest.getAddress());

        List<MenuItem> menuItems = new ArrayList<>();
        for (MenuItemRequest menuItemRequest : restaurantRequest.getMenuItems()) {
            MenuItem menuItem = new MenuItem();
            menuItem.setItemName(menuItemRequest.getName());
            menuItem.setPrice(menuItemRequest.getPrice());
            menuItem.setRestaurant(restaurant);
            menuItems.add(menuItem);
        }
        restaurant.setMenuItems(menuItems);

        return restaurantRepository.save(restaurant); // Saves restaurant along with menu items.
    }
}