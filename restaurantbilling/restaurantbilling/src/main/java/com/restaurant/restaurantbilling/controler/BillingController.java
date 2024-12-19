package com.restaurant.restaurantbilling.controler;

import com.restaurant.restaurantbilling.entity.Bill;
import com.restaurant.restaurantbilling.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/bill")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @PostMapping
    public Bill generateBill(@RequestBody List<Long> itemIds) {
        return billingService.generateBill(itemIds);
    }
}