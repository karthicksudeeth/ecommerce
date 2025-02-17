package com.ecom.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCode {

    @Id
    private String code;
    private boolean isUsed;

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }
}
