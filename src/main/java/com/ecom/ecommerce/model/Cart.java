package com.ecom.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    public void addItem(Item item, int quantity) {
        CartItem cartItem = new CartItem(this, item, quantity);
        this.cartItems.add(cartItem);
    }

    public double getTotalPrice() {
        return cartItems.stream()
                .mapToDouble(cartItem -> cartItem.getItem().getPrice() * cartItem.getQuantity())
                .sum();
    }
}
