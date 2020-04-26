package com.aleyla.shopping.dto;

import com.aleyla.shopping.model.Product;

public class CardProduct {
    private Product product;
    private Integer quantity;

    public CardProduct(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
