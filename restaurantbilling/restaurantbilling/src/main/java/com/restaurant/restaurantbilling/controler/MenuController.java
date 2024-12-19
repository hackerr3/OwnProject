package com.restaurant.restaurantbilling.controler;

import com.restaurant.restaurantbilling.entity.MenuItem;
import com.restaurant.restaurantbilling.service.MenuService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/menu")
class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/{restaurantId}")
    public List<MenuItem> getMenu(@PathVariable Long restaurantId) {
        return menuService.getMenu(restaurantId);
    }


    @DeleteMapping("/{id}")
    public String removeMenuItem(@PathVariable Long id) {
        menuService.removeMenuItem(id);
        return "Item removed successfully.";
    }

    @PostMapping
    public MenuItem addMenuItem(@RequestBody MenuItem menuItem) {
        if (menuItem.getRestaurant() == null || menuItem.getRestaurant().getId() == null) {
            throw new IllegalArgumentException("Restaurant must be provided when adding a menu item.");
        }
        return menuService.addMenuItem(menuItem);
    }

}