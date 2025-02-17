package com.ecom.ecommerce.repository;

import com.ecom.ecommerce.model.DiscountCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountCodeRepository extends JpaRepository<DiscountCode, String> {
    DiscountCode findByCodeAndIsUsedFalse(String code);
}

