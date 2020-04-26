package com.aleyla.shopping.core;

import com.aleyla.shopping.enums.DiscountType;

import java.io.Serializable;

public abstract class DiscountBase implements Serializable {

    private double discount;

    private DiscountType type;

    public DiscountBase(double discount, DiscountType type) {
        this.discount = discount;
        this.type = type;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
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
