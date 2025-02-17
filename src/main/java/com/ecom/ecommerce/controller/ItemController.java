package com.ecom.ecommerce.controller;

import com.ecom.ecommerce.model.Item;
import com.ecom.ecommerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // Add multiple items or a single item
    @PostMapping
    public ResponseEntity<List<Item>> addItems(@RequestBody List<Item> items) {
        List<Item> savedItems = itemService.addItems(items);
        return new ResponseEntity<>(savedItems, HttpStatus.CREATED);
    }

    // Get all items
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    // Get a specific item by id
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return itemService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
