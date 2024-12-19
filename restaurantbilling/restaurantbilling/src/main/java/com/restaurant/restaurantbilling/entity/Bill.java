package com.restaurant.restaurantbilling.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
@Entity
@Data
public class Bill{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<MenuItem> items = new ArrayList<>();

    private double total;

    public Bill() {}

    public Bill(List<MenuItem> items, double total) {
        this.items = items;
        this.total = total;
    }

}
