package com.ecom.ecommerce.controller;

import com.ecom.ecommerce.model.Order;
import com.ecom.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout/{cartId}")
    public ResponseEntity<Order> checkout(@PathVariable Long cartId, @RequestParam(required = false) String discountCode) {
        Order order = orderService.checkout(cartId, discountCode);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
