package com.restaurant.restaurantbilling.repo;

import com.restaurant.restaurantbilling.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByRestaurantName(String restaurantName);
   /* @Query("SELECT r FROM Restaurant r WHERE r.restaurantName = :restaurantName")
    Optional<Restaurant> findByRestaurantName(@Param("restaurantName") String restaurantName);*/

}
