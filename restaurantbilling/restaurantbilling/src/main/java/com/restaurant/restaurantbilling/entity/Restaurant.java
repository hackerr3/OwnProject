package com.restaurant.restaurantbilling.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.*;

@Data
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Let the database handle this ID

    @Version
    private Integer version;


    private String restaurantName;
    private String restaurantAddress;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItem> menuItems = new ArrayList<>();

    public Restaurant() {}
}
