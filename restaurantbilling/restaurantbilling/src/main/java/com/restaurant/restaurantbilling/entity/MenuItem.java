package com.restaurant.restaurantbilling.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Data
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;

    private double price;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public MenuItem() {}

    public MenuItem(String name, double price, Restaurant restaurant) {
        this.itemName = name;
        this.price = price;
        this.restaurant = restaurant;
    }

}

