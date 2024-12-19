package com.restaurant.restaurantbilling.service;

import com.restaurant.restaurantbilling.entity.Bill;
import jakarta.transaction.Transactional;

import java.util.List;

public interface BillingService {
    @Transactional
    Bill generateBill(List<Long> itemIds);
}
