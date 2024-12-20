package com.restaurant.restaurantbilling.service;

import com.restaurant.restaurantbilling.dto.MenuItemDTO;
import com.restaurant.restaurantbilling.dto.RestaurantRequestDTO;
import com.restaurant.restaurantbilling.dto.RestaurantResponseDTO;
import com.restaurant.restaurantbilling.entity.MenuItem;
import com.restaurant.restaurantbilling.entity.Restaurant;
import com.restaurant.restaurantbilling.repo.RestaurantRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

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

    @Override
    public RestaurantResponseDTO addRestaurantWithoutDuplicateItems(RestaurantRequestDTO requestDTO) {
        return null;
    }


    @Override
    public RestaurantResponseDTO addRestaurant(RestaurantRequestDTO requestDTO) {
        // Validate restaurant name
        if (requestDTO.getRestaurantName() == null || requestDTO.getRestaurantName().isEmpty()) {
            throw new IllegalArgumentException("Restaurant name is mandatory");
        }

        // Check for duplicate restaurant name
        Optional<Restaurant> existingRestaurant = restaurantRepository.findByRestaurantName(requestDTO.getRestaurantName());
        if (existingRestaurant.isPresent()) {
            throw new IllegalArgumentException("A restaurant with the name '" + requestDTO.getRestaurantName() + "' already exists");
        }



        // Validate restaurant address
        if (requestDTO.getRestaurantAddress() == null || requestDTO.getRestaurantAddress().isEmpty()) {
            throw new IllegalArgumentException("Restaurant address is mandatory");
        }

        // Validate menu items
        if (requestDTO.getMenuItems() == null || requestDTO.getMenuItems().isEmpty()) {
            throw new IllegalArgumentException("At least one menu item is mandatory");
        }

        // Check for duplicate menu item names
        Map<String, Long> itemNameCounts = requestDTO.getMenuItems().stream()
                .collect(Collectors.groupingBy(MenuItemDTO::getItemName, Collectors.counting()));

        // Find all duplicate menu item names
        List<String> duplicateItemNames = itemNameCounts.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (!duplicateItemNames.isEmpty()) {
                throw new IllegalArgumentException("Duplicate menu item names found: " + String.join(", ", duplicateItemNames));
        }


        // Create a new Restaurant entity
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName(requestDTO.getRestaurantName());
        restaurant.setRestaurantAddress(requestDTO.getRestaurantAddress());

        // Map MenuItems
        List<MenuItem> menuItems = requestDTO.getMenuItems().stream().map(menuItemDTO -> {
            MenuItem menuItem = new MenuItem();
            menuItem.setItemName(menuItemDTO.getItemName());
            menuItem.setPrice(menuItemDTO.getPrice());
            menuItem.setRestaurant(restaurant);
            return menuItem;
        }).collect(Collectors.toList());
        restaurant.setMenuItems(menuItems);

        // Save the restaurant
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        // Map to Response DTO
        RestaurantResponseDTO responseDTO = new RestaurantResponseDTO();
        responseDTO.setRestaurantId(String.valueOf(savedRestaurant.getId()));
        responseDTO.setRestaurantName(savedRestaurant.getRestaurantName());
        responseDTO.setRestaurantAddress(savedRestaurant.getRestaurantAddress());
        responseDTO.setMenuItems(savedRestaurant.getMenuItems().stream().map(menuItem -> {
            MenuItemDTO menuItemDTO = new MenuItemDTO();
            menuItemDTO.setItemName(menuItem.getItemName());
            menuItemDTO.setPrice(menuItem.getPrice());
            return menuItemDTO;
        }).collect(Collectors.toList()));

        return responseDTO;
    }


    @Override
    public RestaurantResponseDTO updateRestaurantWithoutDuplicateItems(Long id, RestaurantRequestDTO requestDTO) {
        // Fetch the existing restaurant
        Restaurant existingRestaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found with ID: " + id));

        // Validate restaurant name
        if (requestDTO.getRestaurantName() == null || requestDTO.getRestaurantName().isEmpty()) {
            throw new IllegalArgumentException("Restaurant name is mandatory");
        }

        // Validate restaurant address
        if (requestDTO.getRestaurantAddress() == null || requestDTO.getRestaurantAddress().isEmpty()) {
            throw new IllegalArgumentException("Restaurant address is mandatory");
        }

        // Validate menu items
        if (requestDTO.getMenuItems() == null || requestDTO.getMenuItems().isEmpty()) {
            throw new IllegalArgumentException("At least one menu item is mandatory");
        }

        // Check for duplicate item names in the menu
        Set<String> itemNames = new HashSet<>();
        for (MenuItemDTO menuItem : requestDTO.getMenuItems()) {
            if (!itemNames.add(menuItem.getItemName())) {
                throw new IllegalArgumentException("Duplicate menu item name: " + menuItem.getItemName());
            }
        }

        // Update the restaurant entity
        existingRestaurant.setRestaurantName(requestDTO.getRestaurantName());
        existingRestaurant.setRestaurantAddress(requestDTO.getRestaurantAddress());

        // Map and update MenuItems
        List<MenuItem> menuItems = requestDTO.getMenuItems().stream().map(menuItemDTO -> {
            MenuItem menuItem = new MenuItem();
            menuItem.setItemName(menuItemDTO.getItemName());
            menuItem.setPrice(menuItemDTO.getPrice());
            menuItem.setRestaurant(existingRestaurant);
            return menuItem;
        }).collect(Collectors.toList());
        existingRestaurant.setMenuItems(menuItems);

        // Save the updated restaurant
        Restaurant savedRestaurant = restaurantRepository.save(existingRestaurant);

        // Map to Response DTO
        RestaurantResponseDTO responseDTO = new RestaurantResponseDTO();
        responseDTO.setRestaurantId(String.valueOf(savedRestaurant.getId()));
        responseDTO.setRestaurantName(savedRestaurant.getRestaurantName());
        responseDTO.setRestaurantAddress(savedRestaurant.getRestaurantAddress());
        responseDTO.setMenuItems(savedRestaurant.getMenuItems().stream().map(menuItem -> {
            MenuItemDTO menuItemDTO = new MenuItemDTO();
            menuItemDTO.setItemName(menuItem.getItemName());
            menuItemDTO.setPrice(menuItem.getPrice());
            return menuItemDTO;
        }).collect(Collectors.toList()));

        return responseDTO;
    }




}