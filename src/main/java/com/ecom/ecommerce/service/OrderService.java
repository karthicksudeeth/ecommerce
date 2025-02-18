package com.ecom.ecommerce.service;

import com.ecom.ecommerce.model.*;
import com.ecom.ecommerce.repository.CartRepository;
import com.ecom.ecommerce.repository.OrderRepository;
import com.ecom.ecommerce.repository.DiscountCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    @Autowired
    private DiscountService discountService;

    public Order checkout(Long cartId, String discountCode) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        double totalAmount = cart.getTotalPrice();
        double discountAmount = 0;

        if (discountCode != null && !discountCode.isEmpty()) {
            if (discountService.validateDiscountCode(discountCode)) {
                discountAmount = totalAmount * 0.1;
                totalAmount -= discountAmount;

                DiscountCode discount = discountCodeRepository.findByCodeAndIsUsedFalse(discountCode);
                if (discount != null) {
                    discount.setIsUsed(true);
                    discountCodeRepository.save(discount);
                }
            }
        }

        Order order = new Order();
        order.setTotalAmount(cart.getTotalPrice());
        order.setDiscountAmount(discountAmount);
        order.setFinalAmount(totalAmount);

        List<OrderItem> orderItems = cart.getCartItems().stream()
                .map(cartItem -> new OrderItem(cartItem, order))
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);

        Order savedOrder = orderRepository.save(order);

        cart.getCartItems().clear();
        cartRepository.save(cart);

        savedOrder.setDiscountAmount(discountAmount);
        return savedOrder;
    }
}
