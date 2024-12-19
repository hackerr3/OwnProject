package com.restaurant.restaurantbilling.service;

import com.restaurant.restaurantbilling.entity.MenuItem;
import com.restaurant.restaurantbilling.entity.Restaurant;
import com.restaurant.restaurantbilling.repo.MenuItemRepository;
import com.restaurant.restaurantbilling.repo.RestaurantRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<MenuItem> getMenu(Long restaurantId) {
        return menuItemRepository.findAll().stream()
                .filter(menuItem -> menuItem.getRestaurant().getId().equals(restaurantId))
                .toList();
    }

    /*@Override
    public MenuItem addMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }*/

    @Override
    public void removeMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }

    @Override
    public MenuItem addMenuItem(MenuItem menuItem) {
        Long restaurantId = menuItem.getRestaurant().getId();
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with ID: " + restaurantId));
        menuItem.setRestaurant(restaurant); // Ensure association
        return menuItemRepository.save(menuItem);
    }
}
