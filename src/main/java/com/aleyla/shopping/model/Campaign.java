package com.aleyla.shopping.model;

import com.aleyla.shopping.enums.DiscountType;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Campaign extends DiscountBase {

    private Category category;

    private Long minQuantity;

    public Campaign(Category category, Long minQuantity, Double discount, DiscountType type) {
        super(discount, type);
        this.category = category;
        this.minQuantity = minQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Long minQuantity) {
        this.minQuantity = minQuantity;
    }


    @Override
    public double calculateDiscount(ShoppingCart shoppingCart) {

        Map<Product, Integer> productsMap = shoppingCart.getProducts();
        Map<Product, Integer> filteredByQantityMap = productsMap.keySet().stream()
                .filter(product -> productsMap.get(product) > minQuantity).collect(Collectors.toMap(p -> p, p -> productsMap.get(p)));
        Map<Product, Integer> filteredByCategoryMap = filteredByQantityMap.keySet().stream()
                .filter(product -> checkByCategory((Product) product)).collect(Collectors.toMap(p -> p, p -> productsMap.get(p)));

        AtomicReference<Double> totalDiscount = new AtomicReference<>((double) 0);
        filteredByCategoryMap.forEach((product, quantity) -> {
            if (getType() == DiscountType.AMOUNT) {
                totalDiscount.updateAndGet(v -> v + getDiscount());
            } else {
                totalDiscount.updateAndGet(v -> v + ((product.getPrice() * quantity) * getDiscount()) / 100);
            }
        });

        return totalDiscount.get();

    }

    private boolean checkByCategory(Product product) {
        Category category = product.getCategory();
        while (category != null) {
            if (category.equals(this.getCategory()))
                return true;
            category = category.getParent();
        }
        return false;
    }


}


