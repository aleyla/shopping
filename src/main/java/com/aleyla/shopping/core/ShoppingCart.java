package com.aleyla.shopping.core;


import com.aleyla.shopping.printer.PrinterUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ShoppingCart {

    private Map<Product, Integer> products = new HashMap<>();
    private double campaignDiscount;
    private double couponDiscount;
    private double totalAmount;
    private double deliveryCost;

    DeliveryCostCalculator deliveryCostCalculator;

    public void addProduct(Product product, Integer quantity) {
        if (products.containsKey(product)) {
            Integer oldQuantity = products.get(product);
            if (quantity == 0) {
                totalAmount -= product.getPrice() * oldQuantity;
                products.remove(product);
            } else {
                totalAmount += product.getPrice() * quantity;
                products.put(product, quantity + oldQuantity);
            }
        } else {
            if (quantity > 0) {
                products.put(product, quantity);
                totalAmount += quantity * product.getPrice();
            }
        }

    }

    public void applyDiscounts(List<Campaign> campaigns) {
        Optional<Double> max = campaigns.stream().map(campaign ->
                campaign.calculateDiscount(this))
                .max(Double::compare);
        campaignDiscount = max.isPresent() ? max.get() : 0;
    }

    public void applyCoupon(Coupon coupon) {
        couponDiscount = coupon.calculateDiscount(this);
    }

    public double getTotalAmountAfterDiscounts() {
        return totalAmount - getTotalDiscount();
    }

    public double getTotalDiscount() {
        return campaignDiscount + couponDiscount;
    }

    public String print() {
        return PrinterUtil.print(this);
    }

    public double getTotalAmountAfterCampaingDiscount() {
        return totalAmount - campaignDiscount;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
