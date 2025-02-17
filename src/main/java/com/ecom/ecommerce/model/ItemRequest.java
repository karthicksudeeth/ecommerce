package com.ecom.ecommerce.model;

public class ItemRequest {

    private Long itemId;
    private int quantity;

    public ItemRequest() {}

    public ItemRequest(Long itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
