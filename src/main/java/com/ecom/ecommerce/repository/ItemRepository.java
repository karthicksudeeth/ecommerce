package com.ecom.ecommerce.repository;

import com.ecom.ecommerce.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
