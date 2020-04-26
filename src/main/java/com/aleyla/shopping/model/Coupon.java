package com.aleyla.shopping.model;

import com.aleyla.shopping.enums.DiscountType;

public class Coupon extends DiscountBase {

    private Double minAmount;

    public Coupon(Double minAmount, Double discount, DiscountType type) {
        super(discount, type);
        this.minAmount = minAmount;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Double minAmount) {
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
