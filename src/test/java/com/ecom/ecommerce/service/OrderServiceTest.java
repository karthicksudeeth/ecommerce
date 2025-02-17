package com.ecom.ecommerce.service;

import com.ecom.ecommerce.model.*;
import com.ecom.ecommerce.repository.CartRepository;
import com.ecom.ecommerce.repository.OrderRepository;
import com.ecom.ecommerce.repository.DiscountCodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private DiscountCodeRepository discountCodeRepository;

    @Mock
    private DiscountService discountService;

    private Cart cart;
    private Item item;
    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cart = new Cart();
        cart.setId(1L);
        item = new Item(1L, "Phone", 500.0);
        cartItem = new CartItem(cart, item, 1);
        cart.getCartItems().add(cartItem);
    }

    @Test
    void testCheckoutWithoutDiscount() {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order order = orderService.checkout(1L, null);
        assertNotNull(order);
        assertEquals(500.0, order.getTotalAmount());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testCheckoutWithDiscount() {
        DiscountCode discountCode = new DiscountCode("DISCOUNT10", false);
        when(cartRepository.findById(1L)).thenReturn(Optional.of(cart));
        when(discountCodeRepository.findByCodeAndIsUsedFalse("DISCOUNT10")).thenReturn(discountCode);
        when(discountService.validateDiscountCode("DISCOUNT10")).thenReturn(true);
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order order = orderService.checkout(1L, "DISCOUNT10");

        assertNotNull(order);
        assertEquals(450.0, order.getTotalAmount());
        assertEquals(50.0, order.getDiscountAmount());
        assertTrue(discountCode.isUsed());
    }
}
