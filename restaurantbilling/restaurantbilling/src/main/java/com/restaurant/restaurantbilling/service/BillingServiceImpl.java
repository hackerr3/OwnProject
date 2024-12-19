package com.restaurant.restaurantbilling.service;

import com.restaurant.restaurantbilling.entity.Bill;
import com.restaurant.restaurantbilling.entity.MenuItem;
import com.restaurant.restaurantbilling.repo.BillRepository;
import com.restaurant.restaurantbilling.repo.MenuItemRepository;
import jakarta.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BillingServiceImpl implements BillingService{

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private BillRepository billRepository;

    @Transactional
    @Override
    public Bill generateBill(List<Long> itemIds) {
        List<MenuItem> items = menuItemRepository.findAllById(itemIds);
        double total = items.stream().mapToDouble(MenuItem::getPrice).sum();
        Bill bill = new Bill(items, total);
        return billRepository.save(bill);
    }
}
