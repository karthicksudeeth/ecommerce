package com.ecom.ecommerce.service;

import com.ecom.ecommerce.model.DiscountCode;
import com.ecom.ecommerce.repository.DiscountCodeRepository;
import com.ecom.ecommerce.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    private int nthOrder = 5;

    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    @Autowired
    private OrderRepository orderRepository;

    public int getOrderCount() {
        return (int) orderRepository.count();
    }


    public boolean isDiscountEligible() {
        int orderCount = getOrderCount();
        return (orderCount + 1) % nthOrder == 0;
    }

    public DiscountCode generateDiscountCode() {
        if (isDiscountEligible()) {
            int orderCount = getOrderCount();
            DiscountCode discountCode = new DiscountCode("DISCOUNT" + (orderCount + 1), false);
            return discountCodeRepository.save(discountCode);
        }
        return null;
    }

    public boolean validateDiscountCode(String code) {
        DiscountCode discountCode = discountCodeRepository.findByCodeAndIsUsedFalse(code);
        return discountCode != null;
    }

    public double applyDiscount(double totalAmount, String code) {
        if (validateDiscountCode(code)) {
            // Mark the code as used
            DiscountCode discountCode = discountCodeRepository.findByCodeAndIsUsedFalse(code);
            discountCode.setIsUsed(true);
            discountCodeRepository.save(discountCode);

            return totalAmount * 0.9;
        }
        return totalAmount;
    }
}
