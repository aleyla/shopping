package com.aleyla.shopping;

import com.aleyla.shopping.enums.DiscountType;
import com.aleyla.shopping.core.*;
import com.aleyla.shopping.core.DeliveryCostCalculator;
import com.aleyla.shopping.core.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class ShoppingApplicationTests {

    ShoppingCart shoppingCart;

    @BeforeEach
    public void beforeAll() {
        shoppingCart = new ShoppingCart();
    }

    @Test
    void should_add_product() {
        Category category = new Category("Category");
        Product product = new Product("Product", 10, category);
        int productQuantity = 3;
        shoppingCart.addProduct(product, productQuantity);
        Assertions.assertTrue(shoppingCart.getProducts().containsKey(product));
        Assertions.assertEquals((int) shoppingCart.getProducts().get(product), productQuantity);
    }

    @Test
    void should_not_add_zero_quantity_product() {
        Category category = new Category("Category");
        Product product = new Product("Product", 10, category);
        shoppingCart.addProduct(product, 0);
        Assertions.assertFalse(shoppingCart.getProducts().containsKey(product));
    }

    @Test
    void should_remove_zero_quantity_product() {
        Category category = new Category("Category");
        Product product = new Product("Product", 10, category);
        shoppingCart.addProduct(product, 5);
        Assertions.assertEquals(shoppingCart.getTotalAmount(), 50);
        shoppingCart.addProduct(product, 2);
        Assertions.assertEquals(shoppingCart.getTotalAmount(), 70);
        shoppingCart.addProduct(product, 0);
        Assertions.assertEquals(shoppingCart.getTotalAmount(), 0);
        Assertions.assertFalse(shoppingCart.getProducts().containsKey(product));
    }



    @Test
    void should_apply_discounts() {
        Category category = new Category("Category");
        Product product = new Product("Product", 10, category);
        Campaign campaign1 = new Campaign(category,1L,1, DiscountType.AMOUNT);
        Campaign campaign2 = new Campaign(category,2L,20, DiscountType.RATE);
        shoppingCart.addProduct(product, 10);
        shoppingCart.applyDiscounts(Arrays.asList(campaign1, campaign2));
        Assertions.assertEquals(shoppingCart.getTotalAmountAfterDiscounts(), 80);
    }


    @Test
     void should_apply_coupon() {
        Category category = new Category("Category");
        Product product = new Product("Product", 10, category);
        Coupon coupon = new Coupon(20,10, DiscountType.RATE);
        shoppingCart.addProduct(product, 10);
        shoppingCart.applyCoupon(coupon);
        Assertions.assertEquals(shoppingCart.getTotalDiscount(), 10);
    }

    @Test
    void should_not_apply_coupon_under_min_amount() {
        Category category = new Category("Category");
        Product product = new Product("Product", 10, category);
        Coupon coupon = new Coupon(150,10, DiscountType.RATE);
        shoppingCart.addProduct(product, 10);
        shoppingCart.applyCoupon(coupon);
        Assertions.assertEquals(shoppingCart.getTotalDiscount(), 0);
    }

    @Test
    void should_apply_campaign_and_coupon() {
        Category category = new Category("Category");
        Product product = new Product("Product", 10, category);
        Coupon coupon = new Coupon(20,10, DiscountType.AMOUNT);
        Campaign campaign2 = new Campaign(category,2L,20, DiscountType.RATE);

        shoppingCart.addProduct(product, 10);
        shoppingCart.applyDiscounts(Arrays.asList(campaign2));
        shoppingCart.applyCoupon(coupon);

        Assertions.assertEquals(shoppingCart.getTotalAmountAfterDiscounts(), 70);
    }

    @Test
    void should_calculate_for_cart_delivery_cost() {
        Category category = new Category("Category");
        Category category2 = new Category("Category2");
        Product product = new Product("Product1", 10, category);
        Product product2 = new Product("Product2", 10, category2);
        shoppingCart.addProduct(product, 10);
        shoppingCart.addProduct(product2, 10);
        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(2.99,1,2);
        deliveryCostCalculator.calculateFor(shoppingCart);
        Assertions.assertEquals(shoppingCart.getDeliveryCost(), 8.99);
    }

    @Test
    void should_card_print() {
        Category category = new Category("Category1");
        Category category2 = new Category("Category2");
        Product product = new Product("Product1", 10, category);
        Product product2 = new Product("Product2", 10, category2);
        Product product3 = new Product("Product3", 50, category2);
        shoppingCart.addProduct(product, 10);
        shoppingCart.addProduct(product2, 10);
        shoppingCart.addProduct(product3, 5);

        Coupon coupon = new Coupon(20,10, DiscountType.AMOUNT);
        Campaign campaign2 = new Campaign(category,2L,20, DiscountType.RATE);

        shoppingCart.applyDiscounts(Arrays.asList(campaign2));
        shoppingCart.applyCoupon(coupon);

        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(2.99,1,2);
        deliveryCostCalculator.calculateFor(shoppingCart);
        shoppingCart.print();

        Assertions.assertTrue(true);
    }


}
