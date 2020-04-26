package com.aleyla.shopping.core;

import com.aleyla.shopping.enums.DiscountType;

public class Coupon extends DiscountBase {

    private double minAmount;

    public Coupon(double minAmount, double discount, DiscountType type) {
        super(discount, type);
        this.minAmount = minAmount;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }


    @Override
    public double calculateDiscount(ShoppingCart shoppingCart) {
        if (shoppingCart.getTotalAmountAfterCampaingDiscount() < minAmount) {
            return 0;
        }
        if (getType() == DiscountType.AMOUNT) {
            return getDiscount();
        } else {
            return (shoppingCart.getTotalAmountAfterCampaingDiscount() * getDiscount()) / 100;
        }
    }
}
