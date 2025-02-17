package com.ecom.ecommerce.controller;

import com.ecom.ecommerce.model.Cart;
import com.ecom.ecommerce.model.ItemRequest;
import com.ecom.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/{cartId}/add")
    public Cart addItemsToCart(@PathVariable Long cartId, @RequestBody List<ItemRequest> items) {
        return cartService.addItemsToCart(cartId, items);
    }

    @GetMapping("/{cartId}/total")
    public double getCartTotal(@PathVariable Long cartId) {
        return cartService.getCartTotal(cartId);
    }

    @GetMapping("/{cartId}")
    public Cart getCartById(@PathVariable Long cartId) {
        return cartService.getCartById(cartId);
    }

    @PostMapping("/create")
    public Cart createCart() {
        return cartService.createCart();
    }

}
