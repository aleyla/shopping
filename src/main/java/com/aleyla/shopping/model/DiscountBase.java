package com.aleyla.shopping.model;

import com.aleyla.shopping.enums.DiscountType;

import java.io.Serializable;

public abstract class DiscountBase implements Serializable {

    private Double discount;

    private DiscountType type;

    public DiscountBase(Double discount, DiscountType type) {
        this.discount = discount;
        this.type = type;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public DiscountType getType() {
        return type;
    }

    public void setType(DiscountType type) {
        this.type = type;
    }


    public abstract double calculateDiscount(ShoppingCart shoppingCart);
}
