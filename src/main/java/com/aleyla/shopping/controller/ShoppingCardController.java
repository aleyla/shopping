package com.aleyla.shopping.controller;

import com.aleyla.shopping.dto.CardProduct;
import com.aleyla.shopping.model.Campaign;
import com.aleyla.shopping.model.Coupon;
import com.aleyla.shopping.model.ShoppingCart;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("card/v1")
public class ShoppingCardController {

    private ShoppingCart shoppingCart;

    public ShoppingCardController(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @PostMapping
    public void save(@RequestBody CardProduct cardProduct) {
        shoppingCart.addProduct(cardProduct.getProduct(), cardProduct.getQuantity());
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
