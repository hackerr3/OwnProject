package com.restaurant.restaurantbilling.service;

import com.restaurant.restaurantbilling.entity.MenuItem;

import java.util.List;

public interface MenuService {

    List<MenuItem> getMenu(Long restaurantId);

    MenuItem addMenuItem(MenuItem menuItem);

    void removeMenuItem(Long id);
}
