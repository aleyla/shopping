package com.aleyla.shopping.controller;

import com.aleyla.shopping.dto.CartProduct;
import com.aleyla.shopping.model.Campaign;
import com.aleyla.shopping.model.Coupon;
import com.aleyla.shopping.model.ShoppingCart;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart/v1")
public class ShoppingCartController {

    private ShoppingCart shoppingCart;

    public ShoppingCartController(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @PostMapping
    public void addProduct(@RequestBody CartProduct cartProduct) {
        shoppingCart.addProduct(cartProduct.getProduct(), cartProduct.getQuantity());
    }

    @PostMapping(path = "applyDiscounts")
    public void applyDiscounts(@RequestBody List<Campaign> campaigns) {
        shoppingCart.applyDiscounts(campaigns);
    }

    @PostMapping(path = "applyCoupon")
    public void applyCoupon(@RequestBody Coupon coupon) {
        shoppingCart.applyCoupon(coupon);
    }

    @GetMapping
    public String print() {
        return shoppingCart.print();
    }

}
