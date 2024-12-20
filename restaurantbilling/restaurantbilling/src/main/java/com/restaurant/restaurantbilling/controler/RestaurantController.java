package com.restaurant.restaurantbilling.controler;

import com.restaurant.restaurantbilling.dto.RestaurantRequestDTO;
import com.restaurant.restaurantbilling.dto.RestaurantResponseDTO;
import com.restaurant.restaurantbilling.entity.Restaurant;
import com.restaurant.restaurantbilling.service.RestaurantService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getRestaurants() {
        return restaurantService.getRestaurants();
    }




    @PostMapping("/restaurants/add")
    public ResponseEntity<RestaurantResponseDTO> addRestaurant(@RequestBody RestaurantRequestDTO requestDTO) {
        RestaurantResponseDTO responseDTO = restaurantService.addRestaurant(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }




    @PutMapping("/restaurants/update/{id}")
    public ResponseEntity<RestaurantResponseDTO> updateRestaurant(
            @PathVariable Long id, @RequestBody RestaurantRequestDTO requestDTO) {
        RestaurantResponseDTO responseDTO = restaurantService.updateRestaurantWithoutDuplicateItems(id, requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}