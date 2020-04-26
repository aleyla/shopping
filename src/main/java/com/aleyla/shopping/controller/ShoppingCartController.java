package com.aleyla.shopping.controller;

import com.aleyla.shopping.core.Campaign;
import com.aleyla.shopping.core.Coupon;
import com.aleyla.shopping.dto.CartProduct;
import com.aleyla.shopping.service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart/v1")
public class ShoppingCartController {


    private ShoppingCartService shoppingCartService;


    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    public void addProduct(@RequestBody CartProduct cartProduct) {
        shoppingCartService.addProduct(cartProduct);
    }

    @PostMapping(path = "applyDiscounts")
    public void applyDiscounts(@RequestBody List<Campaign> campaigns) {
        shoppingCartService.applyDiscounts(campaigns);
    }

    @PostMapping(path = "applyCoupon")
    public void applyCoupon(@RequestBody Coupon coupon) {
        shoppingCartService.applyCoupon(coupon);
    }

    @GetMapping
    public String print() {
        return shoppingCartService.print();
    }

}
