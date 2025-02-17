package com.ecom.ecommerce.service;

import com.ecom.ecommerce.model.Cart;
import com.ecom.ecommerce.model.CartItem;
import com.ecom.ecommerce.model.Item;
import com.ecom.ecommerce.model.ItemRequest;
import com.ecom.ecommerce.repository.CartRepository;
import com.ecom.ecommerce.repository.ItemRepository;
import com.ecom.ecommerce.repository.CartItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    private Cart cart;
    private Item item;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cart = new Cart();
        cart.setId(1L);

        item = new Item(1L, "Laptop", 1000.0);
    }

    @Test
    void testCreateCart() {
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);
        Cart createdCart = cartService.createCart();
        assertNotNull(createdCart);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void testAddItemsToCart() {
        ItemRequest itemRequest = new ItemRequest(1L, 2);
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        cartService.addItemsToCart(1L, List.of(itemRequest));
        assertFalse(cart.getCartItems().isEmpty());
        assertEquals(2, cart.getCartItems().get(0).getQuantity());
    }

    @Test
    void testGetCartTotal() {
        cart.addItem(item, 2);
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        double total = cartService.getCartTotal(1L);
        assertEquals(2000.0, total);
    }
}
