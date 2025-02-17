package com.ecom.ecommerce.service;

import com.ecom.ecommerce.model.Cart;
import com.ecom.ecommerce.model.CartItem;
import com.ecom.ecommerce.model.Item;
import com.ecom.ecommerce.model.ItemRequest;
import com.ecom.ecommerce.repository.CartItemRepository;
import com.ecom.ecommerce.repository.CartRepository;
import com.ecom.ecommerce.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart addItemsToCart(Long cartId, List<ItemRequest> itemRequests) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        for (ItemRequest itemRequest : itemRequests) {
            Item item = itemRepository.findById(itemRequest.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            // Check if the item is already in the cart
            CartItem existingCartItem = cart.getCartItems().stream()
                    .filter(cartItem -> cartItem.getItem().getId().equals(item.getId()))
                    .findFirst()
                    .orElse(null);

            if (existingCartItem != null) {
                // Increase quantity if item already exists
                existingCartItem.increaseQuantity(itemRequest.getQuantity());
            } else {
                // Create a new cart item if it does not exist
                CartItem newCartItem = new CartItem(cart, item, itemRequest.getQuantity());
                cart.getCartItems().add(newCartItem);
            }
        }

        return cartRepository.save(cart);
    }

    public double getCartTotal(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        return cart.getTotalPrice();
    }

    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }


}
