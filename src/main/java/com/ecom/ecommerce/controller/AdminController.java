package com.ecom.ecommerce.controller;
import com.ecom.ecommerce.model.DiscountCode;
import com.ecom.ecommerce.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private DiscountService discountService;

    @PostMapping("/generateDiscount")
    public String generateDiscountCode() {
        DiscountCode discountCode = discountService.generateDiscountCode();
        if (discountCode != null) {
            return "Discount Code: " + discountCode.getCode();
        }
        return "No discount code available at the moment.";
    }

    @GetMapping("/analytics")
    public String getAnalytics() {
        return "Total orders: " + discountService.getOrderCount() + ", Total discount applied: 10% per order";
    }
}

