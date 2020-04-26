package com.aleyla.shopping.service;

import com.aleyla.shopping.dto.CartProduct;
import com.aleyla.shopping.core.Campaign;
import com.aleyla.shopping.core.Coupon;
import com.aleyla.shopping.core.DeliveryCostCalculator;
import com.aleyla.shopping.core.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    public static final double FIXED_COST = 2.99;
    public static final int COST_PER_PRODUCT = 2;
    public static final int COST_PER_DELIVERY = 2;

    private ShoppingCart shoppingCart;

    public ShoppingCartService(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void addProduct(CartProduct cartProduct) {
        shoppingCart.addProduct(cartProduct.getProduct(), cartProduct.getQuantity());
    }

    public void applyDiscounts(List<Campaign> campaigns) {
        shoppingCart.applyDiscounts(campaigns);
    }

    public void applyCoupon(Coupon coupon) {
        shoppingCart.applyCoupon(coupon);
    }

    public String print() {
        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(FIXED_COST, COST_PER_PRODUCT, COST_PER_DELIVERY);
        deliveryCostCalculator.calculateFor(shoppingCart);
        return shoppingCart.print();
    }
}
