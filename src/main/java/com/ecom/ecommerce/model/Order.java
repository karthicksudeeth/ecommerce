package com.ecom.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Table(name = "orders")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItem> orderItems;

    private double totalAmount;
    private double discountAmount;

    public Order(List<CartItem> cartItems, double totalAmount) {
        this.orderItems = cartItems.stream()
                .map(cartItem -> new OrderItem(cartItem, this))
                .collect(Collectors.toList());
        this.totalAmount = totalAmount;
    }
}
