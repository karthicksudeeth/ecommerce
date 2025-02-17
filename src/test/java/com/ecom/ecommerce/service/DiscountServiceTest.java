package com.ecom.ecommerce.service;

import com.ecom.ecommerce.model.DiscountCode;
import com.ecom.ecommerce.repository.DiscountCodeRepository;
import com.ecom.ecommerce.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DiscountServiceTest {

    @InjectMocks
    private DiscountService discountService;

    @Mock
    private DiscountCodeRepository discountCodeRepository;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateDiscountCode() {
        when(orderRepository.count()).thenReturn(4L);
        when(discountCodeRepository.save(any(DiscountCode.class))).thenAnswer(invocation -> invocation.getArgument(0));

        DiscountCode discountCode = discountService.generateDiscountCode();
        assertNotNull(discountCode);
        assertEquals("DISCOUNT5", discountCode.getCode());
    }

    @Test
    void testValidateDiscountCode() {
        when(discountCodeRepository.findByCodeAndIsUsedFalse("DISCOUNT5"))
                .thenReturn(new DiscountCode("DISCOUNT5", false));

        assertTrue(discountService.validateDiscountCode("DISCOUNT5"));
    }

    @Test
    void testApplyDiscount() {
        DiscountCode discountCode = new DiscountCode("DISCOUNT5", false);
        when(discountCodeRepository.findByCodeAndIsUsedFalse("DISCOUNT5")).thenReturn(discountCode);

        double discountedPrice = discountService.applyDiscount(1000.0, "DISCOUNT5");

        assertEquals(900.0, discountedPrice);
        assertTrue(discountCode.isUsed());
        verify(discountCodeRepository, times(1)).save(any(DiscountCode.class));
    }
}
